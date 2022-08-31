package gcom.gerencial.faturamento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.jboss.logging.Logger;

import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.batch.UnidadeProcessamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.fachada.Fachada;
import gcom.faturamento.ControladorFaturamentoFINAL;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.IRepositorioFaturamento;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.ResumoFaturamentoSimulacao;
import gcom.faturamento.ResumoFaturamentoSituacaoEspecial;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaPK;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.FiltroContaImpostosDeduzidos;
import gcom.faturamento.conta.IContaCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.FiltroCreditoRealizado;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCobrado;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocal;
import gcom.gerencial.cadastro.ControladorGerencialCadastroLocalHome;
import gcom.gerencial.cadastro.GEmpresa;
import gcom.gerencial.cadastro.IRepositorioGerencialCadastro;
import gcom.gerencial.cadastro.RepositorioGerencialCadastroHBM;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.cobranca.IRepositorioGerencialCobranca;
import gcom.gerencial.cobranca.RepositorioGerencialCobrancaHBM;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.gerencial.faturamento.bean.FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoAguaEsgotoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoCreditosHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoCreditosSetores;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoDebitosACobrarHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoGuiaPagamentoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoGuiaPagamentoNovoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoImpostosHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoOutrosHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoPorAnoHelper;
import gcom.gerencial.faturamento.bean.ResumoFaturamentoSituacaoEspecialHelper;
import gcom.gerencial.faturamento.bean.ResumoReFaturamentoHelper;
import gcom.gerencial.faturamento.bean.ResumoReFaturamentoNovoHelper;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.faturamento.credito.GCreditoTipo;
import gcom.gerencial.faturamento.debito.GDebitoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

public class ControladorGerencialFaturamentoSEJB extends ControladorComum {
    private static final long serialVersionUID = -5996920706815852008L;
    
    private static Logger logger = Logger.getLogger(ControladorGerencialFaturamentoSEJB.class);    

    private IRepositorioGerencialCobranca repositorioGerencialCobranca = null;
	private IRepositorioGerencialCadastro repositorioGerencialCadastro = null;
	private IRepositorioGerencialFaturamento repositorioGerencial = null;
	private IRepositorioFaturamento repositorioFaturamento = null;
	private IRepositorioGerencialFaturamento repositorioGerencialFaturamento = null;

	public void ejbCreate() throws CreateException {
		repositorioGerencial = RepositorioGerencialFaturamentoHBM.getInstancia();
		repositorioGerencialFaturamento = RepositorioGerencialFaturamentoHBM.getInstancia();
		repositorioGerencialCobranca = RepositorioGerencialCobrancaHBM.getInstancia();
		repositorioGerencialCadastro = RepositorioGerencialCadastroHBM.getInstancia();
		repositorioFaturamento = RepositorioFaturamentoHBM.getInstancia();
	}

	public Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> recuperaResumoSituacaoEspecialFaturamento(
			ConsultarResumoSituacaoEspecialHelper helper)
			throws ControladorException {
		
		Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = null;
		
		try {
			
			Integer[] idsSituacaoTipo = helper.getSituacaoTipo();
			
			if (idsSituacaoTipo != null) {
				if (idsSituacaoTipo.length == 1 && idsSituacaoTipo[0] == 0) {
					idsSituacaoTipo = null;
				}
			}
			
			Integer[] idsSituacaoMotivo = helper.getSituacaoMotivo();
			
			if (idsSituacaoMotivo != null) {
				if (idsSituacaoMotivo.length == 1 && idsSituacaoMotivo[0] == 0) {
					idsSituacaoMotivo = null;
				}
			}
			
			helper.setSituacaoTipo(idsSituacaoTipo);
			helper.setSituacaoMotivo(idsSituacaoMotivo);
			
			resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = this.pesquisarResumoFaturamentoSituacaoEspecialConsultaGerenciaRegionalHelper(helper);

			for (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper helperGerencia : resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) {
				
				// Objeto utilizado para as consultar internas dos loops
				ConsultarResumoSituacaoEspecialHelper helperClone = new ConsultarResumoSituacaoEspecialHelper();
				
				helperClone.setIdGerenciaRegional(helperGerencia.getIdGerenciaRegional().toString());
				helperClone.setIdUnidadeNegocio(helper.getIdUnidadeNegocio());
				helperClone.setIdLocalidadeInicial(helper.getIdLocalidadeInicial());
				helperClone.setIdLocalidadeFinal(helper.getIdLocalidadeFinal());
				helperClone.setCodigoSetorComercialInicial(helper.getCodigoSetorComercialInicial());
				helperClone.setCodigoSetorComercialFinal(helper.getCodigoSetorComercialFinal());
				helperClone.setCodigoRotaInicial(helper.getCodigoRotaInicial());
				helperClone.setCodigoRotaFinal(helper.getCodigoRotaFinal());
				helperClone.setSituacaoTipo(helper.getSituacaoTipo());
				helperClone.setSituacaoMotivo(helper.getSituacaoMotivo());
				
				Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper = this
						.pesquisarResumoFaturamentoSituacaoEspecialConsultaUnidadeNegocioHelper(helperClone);
		
				helperGerencia.setResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper(resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper);
				
				BigDecimal totalPercentualGerencia = new BigDecimal("0.00");
				BigDecimal totalFatEstimadoGerencia = new BigDecimal("0.00");
				Integer totalQtLigacoesGerencia = new Integer("0");
				
				for (ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio : resumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper) {
					
					helperClone.setIdUnidadeNegocio(helperUnidadeNegocio.getIdUnidadeNegocio().toString());
					
				
					Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper = this
							.pesquisarResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(helperClone);
				
					helperUnidadeNegocio.setResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper);
				
					BigDecimal totalPercentualUnidadeNegocio = new BigDecimal("0.00");
					BigDecimal totalFatEstimadoUnidadeNegocio = new BigDecimal("0.00");
					Integer totalQtLigacoesUnidadeNegocio = new Integer("0");
				
					for (ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper helperLocalidade : resumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper) {
					
						helperClone.setIdLocalidadeInicial(helperLocalidade.getIdLocalidade().toString());
						helperClone.setIdLocalidadeFinal(helperLocalidade.getIdLocalidade().toString());
					
						Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper = this
								.pesquisarResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(helperClone);
					
						helperLocalidade.setResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper);
					
						BigDecimal totalPercentualLocalidade = new BigDecimal("0.00");
						BigDecimal totalFatEstimadoLocalidade = new BigDecimal("0.00");
						Integer totalQtLigacoesLocalidade = new Integer("0");
					
						for (ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial : resumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper) {
						
							helperClone.setCodigoSetorComercialInicial(helperSetorComercial.getCodigoSetorComercial().toString());
							helperClone.setCodigoSetorComercialFinal(helperSetorComercial.getCodigoSetorComercial().toString());
						
							Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper = this
									.pesquisarResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(helperClone);
					
							helperSetorComercial.setResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper);
					
							BigDecimal totalPercentualSetorComercial = new BigDecimal("0.00");
							BigDecimal totalFatEstimadoSetorComercial = new BigDecimal("0.00");
							Integer totalQtLigacoesSetorComercial = new Integer("0");
						
							for (ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper helperSitTipo : resumoFaturamentoSituacaoEspecialConsultaSitTipoHelper) {

								Integer[] idSituacaoTipo = new Integer[1];
								idSituacaoTipo[0] = helperSitTipo.getIdSituacaoTipo();
						
								helperClone.setSituacaoTipo(idSituacaoTipo);
						
								Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> resumoFaturamentoSituacaoEspecialConsultaMotivoHelper = this
										.pesquisarResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(helperClone);
						
								helperSitTipo.setResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(resumoFaturamentoSituacaoEspecialConsultaMotivoHelper);
						
								BigDecimal totalPercentualSitTipo = new BigDecimal("0.00");
								BigDecimal totalFatEstimadoSitTipo = new BigDecimal("0.00");
								Integer totalQtLigacoesSitTipo = new Integer("0");
						
								for (ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper helperMotivo : resumoFaturamentoSituacaoEspecialConsultaMotivoHelper) {
							
									// Calculando o Faturamento Estimado por Motivo
									Integer anoMesInicio = helperMotivo.getAnoMesInicio() - 1;
							
									Collection<ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper> resumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper = this
											.pesquisarResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper(helperClone, anoMesInicio);
							
									BigDecimal fatEstimado = (BigDecimal) resumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper
											.iterator().next().getFaturamentoEstimado().setScale(2, RoundingMode.HALF_UP);
									String fatEstimadoFormatado = Util.formatarMoedaReal(fatEstimado);
									helperMotivo.setFaturamentoEstimado(fatEstimado);
									helperMotivo.setValorFaturamentoEstimadoFormatado(fatEstimadoFormatado);
							
									if (fatEstimado != null) {
										totalFatEstimadoSitTipo = totalFatEstimadoSitTipo.add(fatEstimado);
									}
							
									// Calculando a Qt de Ligacoes por Motivo
									Integer anoMesInicioReal = helperMotivo.getAnoMesInicio();
									Integer qtLigacoes = this.repositorioGerencialFaturamento
											.pesquisarResumoFaturamentoSituacaoEspecialConsultaQtLigacoes(helperClone, anoMesInicioReal);
									helperMotivo.setQtLigacoes(qtLigacoes);
							
									if (qtLigacoes != null) {
										totalQtLigacoesSitTipo = totalQtLigacoesSitTipo	+ qtLigacoes;
									}
							
									BigDecimal qtParalizada = new BigDecimal(
											helperMotivo.getQtParalisada());
							
									// calculando o percentual
									BigDecimal i = new BigDecimal("100");
									BigDecimal percentual = new BigDecimal("0.00");
							
									if (qtParalizada != null && qtLigacoes != null && qtLigacoes != 0) {
										BigDecimal qtLigacoesBigDecimal = new BigDecimal(qtLigacoes);
										percentual = (qtParalizada.multiply(i));
										percentual = percentual.divide(qtLigacoesBigDecimal, 2, RoundingMode.HALF_UP);
									}
							
									helperMotivo.setPercentual(percentual);
							
								}
						
								helperSitTipo.setTotalFatEstimadoSitTipo(totalFatEstimadoSitTipo);
								totalFatEstimadoSetorComercial = totalFatEstimadoSetorComercial
									.add(totalFatEstimadoSitTipo).setScale(2,RoundingMode.HALF_UP);
						
								// total Qt ligacoes
								helperSitTipo.setTotalQtLigacoesSitTipo(totalQtLigacoesSitTipo);
								totalQtLigacoesSetorComercial = totalQtLigacoesSetorComercial + totalQtLigacoesSitTipo;
						
								// total percentual Situacao Tipo
								BigDecimal qtParalizadaSitTipo = new BigDecimal(
								helperSitTipo.getTotalSituacaoTipo());
								BigDecimal i = new BigDecimal("100");
						
								if (qtParalizadaSitTipo != null
										&& totalQtLigacoesSitTipo != null && totalQtLigacoesSitTipo != 0) {
									totalPercentualSitTipo = (qtParalizadaSitTipo.multiply(i));
									totalPercentualSitTipo = totalPercentualSitTipo
										.divide(new BigDecimal(totalQtLigacoesSitTipo), 2,
											RoundingMode.HALF_UP);
								}
						
								helperSitTipo
									.setTotalPercentualSitTipo(totalPercentualSitTipo);
						
								helperClone.setSituacaoTipo(helper.getSituacaoTipo());
						
							}
						
							// total fat estimado Setor Comercial
							helperSetorComercial.setTotalFatEstimadoSetorComercial(totalFatEstimadoSetorComercial);
							totalFatEstimadoLocalidade = totalFatEstimadoLocalidade.add(
									totalFatEstimadoSetorComercial).setScale(2,RoundingMode.HALF_UP);
						
							// total ligacoes Setor Comercial
							helperSetorComercial
									.setTotalQtLigacoesSetorComercial(totalQtLigacoesSetorComercial);
						
							totalQtLigacoesLocalidade = totalQtLigacoesLocalidade + totalQtLigacoesSetorComercial;

							// total percentual Setor Comercial
							BigDecimal qtParalizadaSetorComercial = new BigDecimal(
									helperSetorComercial.getTotalSetorComercial());
							BigDecimal i = new BigDecimal("100");
						
							if (qtParalizadaSetorComercial != null
									&& totalQtLigacoesSetorComercial != null
									&& totalQtLigacoesSetorComercial != 0) {
								totalPercentualSetorComercial = (qtParalizadaSetorComercial.multiply(i));
								totalPercentualSetorComercial = totalPercentualSetorComercial
										.divide(new BigDecimal(totalQtLigacoesSetorComercial), 2,
												RoundingMode.HALF_UP);
							}
						
							helperSetorComercial.setTotalPercentualSetorComercial(totalPercentualSetorComercial);
						
							helperClone.setCodigoSetorComercialInicial(helper.getCodigoSetorComercialInicial());
							helperClone.setCodigoSetorComercialFinal(helper.getCodigoSetorComercialFinal());
						
						}
					
						// total fat estimado Localidade
						helperLocalidade.setTotalFatEstimadoLocalidade(totalFatEstimadoLocalidade);
						totalFatEstimadoUnidadeNegocio = totalFatEstimadoUnidadeNegocio.add(
								totalFatEstimadoLocalidade).setScale(2,RoundingMode.HALF_UP);
					
						// total ligacoes Localidade
						helperLocalidade
								.setTotalQtLigacoesLocalidade(totalQtLigacoesLocalidade);
					
						totalQtLigacoesUnidadeNegocio = totalQtLigacoesUnidadeNegocio + totalQtLigacoesLocalidade;

						// total percentual Localidade
						BigDecimal qtParalizadaLocalidade = new BigDecimal(
								helperLocalidade.getTotalLocalidade());
						BigDecimal i = new BigDecimal("100");
					
						if (qtParalizadaLocalidade != null
							&& totalQtLigacoesLocalidade != null
							&& totalQtLigacoesLocalidade != 0) {
							totalPercentualLocalidade = (qtParalizadaLocalidade.multiply(i));
							totalPercentualLocalidade = totalPercentualLocalidade
									.divide(new BigDecimal(totalQtLigacoesLocalidade), 2,
										RoundingMode.HALF_UP);
						}
					
						helperLocalidade.setTotalPercentualLocalidade(totalPercentualLocalidade);
					
						helperClone.setIdLocalidadeInicial(helper.getIdLocalidadeInicial());
						helperClone.setIdLocalidadeFinal(helper.getIdLocalidadeFinal());
					}
				
					// total percentual Unidade Negocio
					helperUnidadeNegocio.setTotalFatEstimadoUnidadeNegocio(totalFatEstimadoUnidadeNegocio);
					totalFatEstimadoGerencia = totalFatEstimadoGerencia.add(
							totalFatEstimadoUnidadeNegocio).setScale(2,RoundingMode.HALF_UP);
				
					// total ligacoes Unidade Negocio
					helperUnidadeNegocio
							.setTotalQtLigacoesUnidadeNegocio(totalQtLigacoesUnidadeNegocio);
				
					totalQtLigacoesGerencia = totalQtLigacoesGerencia + totalQtLigacoesUnidadeNegocio;

					// total percentual Unidade Negocio
					BigDecimal qtParalizadaUnidadeNegocio = new BigDecimal(
							helperUnidadeNegocio.getTotalUnidadeNegocio());
					BigDecimal i = new BigDecimal("100");
				
					if (qtParalizadaUnidadeNegocio != null
						&& totalQtLigacoesUnidadeNegocio != null
						&& totalQtLigacoesUnidadeNegocio != 0) {
						totalPercentualUnidadeNegocio = (qtParalizadaUnidadeNegocio.multiply(i));
						totalPercentualUnidadeNegocio = totalPercentualUnidadeNegocio
								.divide(new BigDecimal(totalQtLigacoesUnidadeNegocio), 2,
									RoundingMode.HALF_UP);
					}
				
					helperUnidadeNegocio.setTotalPercentualUnidadeNegocio(totalPercentualUnidadeNegocio);
				
					helperClone.setIdUnidadeNegocio(helper.getIdUnidadeNegocio());
				
				}
				
				// total percentual Gerencia
				helperGerencia.setTotalFatEstimadoGerencia(totalFatEstimadoGerencia);

				// total das ligacoes Gerencia
				helperGerencia.setTotalQtLigacoesGerencia(totalQtLigacoesGerencia);

				// total percentual Gerencia
				BigDecimal qtParalizadaGerencia = new BigDecimal(helperGerencia
								.getTotalGerenciaRegional());
				BigDecimal i = new BigDecimal("100");
				if (qtParalizadaGerencia != null
						&& totalQtLigacoesGerencia != null
							&& totalQtLigacoesGerencia != 0) {
					totalPercentualGerencia = (qtParalizadaGerencia.multiply(i));
					totalPercentualGerencia = totalPercentualGerencia.divide(
							new BigDecimal(totalQtLigacoesGerencia), 2,RoundingMode.HALF_UP);
				}
				
				helperGerencia
						.setTotalPercentualGerencia(totalPercentualGerencia);
				
				helperClone.setIdGerenciaRegional(helper.getIdGerenciaRegional());
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
		
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaGerenciaRegionalHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaGerenciaRegionalHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper helperGerencia = new ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper();
				
				if (dados[0] != null) {
					helperGerencia.setIdGerenciaRegional((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperGerencia.setGerenciaRegionalDescricaoAbreviada((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperGerencia.setGerenciaRegionalDescricao((String) dados[2]);
				}
				
				if (dados[3] != null) {
					helperGerencia.setTotalGerenciaRegional((Integer) dados[3]);
				}
				
				retorno.add(helperGerencia);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaUnidadeNegocioHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaUnidadeNegocioHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper helperUnidadeNegocio = new ResumoFaturamentoSituacaoEspecialConsultaUnidadeNegHelper();
				
				if (dados[0] != null) {
					helperUnidadeNegocio.setIdUnidadeNegocio((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperUnidadeNegocio.setUnidadeNegocioDescricaoAbreviada((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperUnidadeNegocio.setUnidadeNegocioDescricao((String) dados[2]);
				}
				
				if (dados[3] != null) {
					helperUnidadeNegocio.setTotalUnidadeNegocio((Integer) dados[3]);
				}
				
				retorno.add(helperUnidadeNegocio);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper helperLocalidade = new ResumoFaturamentoSituacaoEspecialConsultaLocalidadeHelper();
				
				if (dados[0] != null) {
					helperLocalidade.setIdLocalidade((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperLocalidade.setLocalidadeDescricao((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperLocalidade.setTotalLocalidade((Integer) dados[2]);
				}
				
				retorno.add(helperLocalidade);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper helperSetorComercial = new ResumoFaturamentoSituacaoEspecialConsultaSetorComercialHelper();
				
				if (dados[0] != null) {
					helperSetorComercial.setCodigoSetorComercial((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperSetorComercial.setSetorComercialDescricao((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperSetorComercial.setTotalSetorComercial((Integer) dados[2]);
				}
				
				retorno.add(helperSetorComercial);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper helperSitTipo = new ResumoFaturamentoSituacaoEspecialConsultaSitTipoHelper();
				
				if (dados[0] != null) {
					helperSitTipo.setIdSituacaoTipo((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperSitTipo.setSituacaoTipoDescricao((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperSitTipo.setTotalSituacaoTipo((Integer) dados[2]);
				}
				
				retorno.add(helperSitTipo);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(ConsultarResumoSituacaoEspecialHelper helper) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper>();
		
		try {
			Collection<Object[]> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaMotivoHelper(helper);
			
			for (Object[] dados : colecaoDados) {
				ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper helperMotivo = new ResumoFaturamentoSituacaoEspecialConsultaMotivoHelper();
				
				if (dados[0] != null) {
					helperMotivo.setIdMotivo((Integer) dados[0]);
				}
				
				if (dados[1] != null) {
					helperMotivo.setMotivoDescricao((String) dados[1]);
				}
				
				if (dados[2] != null) {
					helperMotivo.setAnoMesInicio((Integer) dados[2]);
				}
				
				if (dados[3] != null) {
					helperMotivo.setAnoMesFim((Integer) dados[3]);
				}
				
				if (dados[4] != null) {
					helperMotivo.setQtParalisada((Integer) dados[4]);
				}
				
				retorno.add(helperMotivo);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	private Collection<ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper> pesquisarResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper(ConsultarResumoSituacaoEspecialHelper helper, int anoMesReferencia) {
		Collection<ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper> retorno = new ArrayList<ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper>();
		
		try {
			Collection<BigDecimal> colecaoDados = this.repositorioGerencialFaturamento
					.pesquisarResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper(helper, anoMesReferencia);
			
			if (colecaoDados != null && !colecaoDados.isEmpty()) {
			
					for (BigDecimal faturamentoEstimado : colecaoDados) {
						ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper helperFatEstimado = new ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper();
				
						if (faturamentoEstimado != null) {
								helperFatEstimado.setFaturamentoEstimado(faturamentoEstimado);
						} else {
							helperFatEstimado.setFaturamentoEstimado(new BigDecimal("0.00"));
						}
				
						retorno.add(helperFatEstimado);
					}
			} else {
				ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper helperFatEstimado = new ResumoFaturamentoSituacaoEspecialConsultaFatEstimadoHelper();
				helperFatEstimado.setFaturamentoEstimado(new BigDecimal("0.00"));
				
				retorno.add(helperFatEstimado);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	

	/**
	 * Método que gera o resumo Resumo Situacao Especial Faturamento
	 * 
	 * [UC0341]
	 * 
	 * @author Thiago Toscano
	 * @date 19/04/2006
	 * 
	 */
	public void gerarResumoSituacaoEspecialFaturamento(int idLocalidade,
			int idFuncionalidadeIniciada) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.LOCALIDADE, idLocalidade);

		try {
			this.repositorioGerencial
					.excluirTodosResumoFaturamentoSituacaoEspecial(idLocalidade);

			List<ResumoFaturamentoSituacaoEspecialHelper> listaSimplificada = new ArrayList();
			List<ResumoFaturamentoSituacaoEspecial> listaResumoLigacoesEconomia = new ArrayList();

			List imoveisResumoLigacaoEconomias = this.repositorioGerencial
					.getResumoFaturamentoSituacaoEspecialHelper(idLocalidade);
			// pra cada objeto obter a categoria e o indicador de existência de
			// hidrômetro
			// caso ja tenha um igual soma a quantidade de economias e a
			// quantidade de ligacoes
			for (int i = 0; i < imoveisResumoLigacaoEconomias.size(); i++) {
				Object obj = imoveisResumoLigacaoEconomias.get(i);

				// if (imoveisResumoLigacaoEconomias != null &&
				// imoveisResumoLigacaoEconomias.get(0) != null) {
				// Object obj = imoveisResumoLigacaoEconomias.get(0);

				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;

					ResumoFaturamentoSituacaoEspecialHelper helper = new ResumoFaturamentoSituacaoEspecialHelper(
							(Integer) retorno[0], 
							(Integer) retorno[1],
							(Integer) retorno[2], 
							(Integer) retorno[3],
							(Integer) retorno[4], 
							(Integer) retorno[5],
							(Integer) retorno[6], 
							(Integer) retorno[7],
							(Integer) retorno[8], 
							(Integer) retorno[9],
							(Integer) retorno[10], 
							null, //esfera de poder é colocado abaixo
							(Integer) retorno[11], 
							(Integer) retorno[12],
							(Integer) retorno[13], 
							(Integer) retorno[14]);

					Integer idImovel = helper.getIdImovel();

					// pesquisando a categoria
					// [UC0306] - Obtter principal categoria do imóvel
					Categoria categoria = null;
					categoria = getControladorImovel().obterPrincipalCategoriaImovel(idImovel);

					if (categoria != null) {
						helper.setIdCategoria(categoria.getId());
					}
					
					helper.setIdEsfera( 
						this.repositorioGerencialFaturamento.pesquisarEsferaPoderClienteResponsavelImovel( 
							(Integer) retorno[0] ) );
					
					// se ja existe um objeto igual a ele entao soma as ligacoes
					// e as economias no ja existente
					// um objeto eh igual ao outro se ele tem todos as
					// informacos iguals ( excecao idImovel, quantidadeEconomia,
					// quantidadeLigacoes)
					if (listaSimplificada.contains(helper)) {
						int posicao = listaSimplificada.indexOf(helper);
						ResumoFaturamentoSituacaoEspecialHelper jaCadastrado = (ResumoFaturamentoSituacaoEspecialHelper) listaSimplificada
								.get(posicao);
						
						
						
						
						jaCadastrado.setQuantidadeImovel(jaCadastrado
								.getQuantidadeImovel() + 1);
					} else {
						listaSimplificada.add(helper);
					}
				}
			}

			/**
			 * para todoas as ImovelResumoLigacaoEconomiaHelper cria
			 * ResumoLigacoesEconomia
			 */
			for (int i = 0; i < listaSimplificada.size(); i++) {
				ResumoFaturamentoSituacaoEspecialHelper helper = (ResumoFaturamentoSituacaoEspecialHelper) listaSimplificada
						.get(i);

				// Integer anoMesReferencia = Util
				// .getAnoMesComoInteger(new Date());

				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}

				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				FaturamentoSituacaoTipo faturamentoSituacaoTipo = null;
				if (helper.getIdEspecialFaturamento() != null) {
					faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
					faturamentoSituacaoTipo.setId(helper
							.getIdEspecialFaturamento());
				}

				FaturamentoSituacaoMotivo faturamentoSituacaoMotivo = null;
				if (helper.getIdMotivoSituacaoEspecialFatauramento() != null) {
					faturamentoSituacaoMotivo = new FaturamentoSituacaoMotivo();
					faturamentoSituacaoMotivo.setId(helper
							.getIdMotivoSituacaoEspecialFatauramento());
				}

				Integer anoMesInicioSituacaoEspecial = null;
				if (helper.getAnoMesInicioSituacaoEspecial() != null) {
					anoMesInicioSituacaoEspecial = (helper
							.getAnoMesInicioSituacaoEspecial());
				}

				Integer anoMesFinalSituacaoEspecial = null;
				if (helper.getAnoMesFinalSituacaoEspecial() != null) {
					anoMesFinalSituacaoEspecial = (helper
							.getAnoMesFinalSituacaoEspecial());
				}

				int quantidadeImovel = (helper.getQuantidadeImovel());

				GerenciaRegional gerenciaRegional = null;
				if (helper.getIdGerenciaRegional() != null) {
					gerenciaRegional = new GerenciaRegional();
					gerenciaRegional.setId(helper.getIdGerenciaRegional());
				}

				Localidade localidade = null;
				if (helper.getIdLocalidade() != null) {
					localidade = new Localidade();
					localidade.setId(helper.getIdLocalidade());
				}

				SetorComercial setorComercial = null;
				if (helper.getIdSetorComercial() != null) {
					setorComercial = new SetorComercial();
					setorComercial.setId(helper.getIdSetorComercial());
				}

				Rota rota = null;
				if (helper.getIdRota() != null) {
					rota = new Rota();
					rota.setId(helper.getIdRota());
				}

				Quadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new Quadra();
					quadra.setId(helper.getIdQuadra());
				}

				ImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new ImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}

				LigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new LigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}

				Categoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new Categoria();
					categoria.setId(helper.getIdCategoria());
				}

				EsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new EsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}

				ResumoFaturamentoSituacaoEspecial resumo = new ResumoFaturamentoSituacaoEspecial(
						codigoSetorComercial, numeroQuadra,
						anoMesInicioSituacaoEspecial,
						anoMesFinalSituacaoEspecial, quantidadeImovel,
						new Date(), gerenciaRegional, localidade,
						setorComercial, rota, quadra, imovelPerfil,
						ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
						esferaPoder, faturamentoSituacaoTipo,
						faturamentoSituacaoMotivo);

				// ResumoFaturamentoSituacaoEspecialHelper
				// resumoLigacoesEconomia = new
				// ResumoFaturamentoSituacaoEspecialHelper(anoMesReferencia,
				// codigoSetorComercial, numeroQuadra, indicadorHidrometro,
				// quantidadeLigacoes,
				// quantidadeEconomias, gerenciaRegional, localidade,
				// setorComercial, rota, quadra, imovelPerfil,
				// ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
				// esferaPoder);

				listaResumoLigacoesEconomia.add(resumo);
			}

			this.repositorioGerencial
					.inserirResumoFaturamentoSituacaoEspecial(listaResumoLigacoesEconomia);

			// --------------------------------------------------------
			//
			// Registrar o fim da execução da Unidade de Processamento
			//
			// --------------------------------------------------------
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceção que o processo
			// batch venha a lançar e garantir que a unidade de processamento do
			// batch será atualizada com o erro ocorrido

			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
	}

	public List consultarResumoAnaliseFaturamento(
			InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper)
			throws ControladorException {
		try {

			// [FS0001] Verificar existência de dados para o ano/mês de
			// referência retornado
			String resumo = ResumoFaturamentoSimulacao.class.getName();
			Integer countResumoAnalise = repositorioGerencialCobranca
					.verificarExistenciaAnoMesReferenciaResumo(
							informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia(), resumo);
			if (countResumoAnalise == null || countResumoAnalise == 0) {
				throw new ControladorException(
						"atencao.nao_existe_resumo_analise_faturamento",
						null,
						Util
								.formatarAnoMesParaMesAno(informarDadosGeracaoRelatorioConsultaHelper
										.getAnoMesReferencia()));
			}

			List retorno = repositorioGerencialFaturamento
					.consultarResumoAnaliseFaturamento(informarDadosGeracaoRelatorioConsultaHelper);

			// [FS0007] Nenhum registro encontrado
			if (retorno == null || retorno.isEmpty()) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

			return retorno;

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
	 * Método que gera o resumo do Faturamento
	 * 
	 * [UC0571] - Gerar Resumo do Faturamento
	 * 
	 * @author Marcio Roberto, Ivan Sergio
	 * @date 12/05/2007, 09/09/2008
	 * @alteracao: 09/09/2008 - O list de resumoFaturamentoAguaEsgoto esta realizando a mesma consulta do list resumo
	 * 			   com a unica diferenca de trazer a quantidade de economias do imovel. Foi adicionado o
	 * 			   valor na consulta do list resumo e todo o processo passa a usar esse list.
	 * 
	 * @param idLocalidade 
	 * @param anoMes 
	 *  
	 */
	public void gerarResumoFaturamentoAguaEsgoto(int idSetor,
			int idFuncionalidadeIniciada, int anoMes) throws ControladorException {

		
		int idUnidadeIniciada = 0;
		Integer idImovelError = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
		try {
			// Listas de Controle
			List<ResumoFaturamentoAguaEsgotoHelper> listaSimplificadaFaturamentoAguaEsgoto = new ArrayList();
			//List<UnResumoFaturamento> listaResumoFaturamentoAguaEsgoto = new ArrayList();
			List<ResumoFaturamentoCreditosSetores> listaSimplificadaFaturamentoCreditosSetores = new ArrayList();
			// Indices da paginacao  1111

			int indice = 0;
			int qtRegistros = 200;
			// flag da paginacao 
			boolean flagTerminou = false;
			// contador de paginacao(informativo no debug)
			int count = 0;
			
			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencial( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				UnResumoFaturamento.class.getName(), "referencia", idSetor );				
			
			// inicio do processamento
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			while(!flagTerminou) {
				
				count++;
				
				List resumo = this.repositorioGerencialFaturamento
				.getContasResumoFaturamentoAguaEsgoto( idSetor, anoMes, indice, qtRegistros);
				
				// Resumo Faturamento Outros --------------------------------------------------------------------
				this.gerarResumoFaturamentoOutros(idSetor, anoMes, indice, qtRegistros, resumo);
				
				// Resumo Faturamento Impostos ------------------------------------------------------------------
				this.gerarResumoFaturamentoImpostos(idSetor, anoMes, indice, qtRegistros, resumo);
				
				// Resumo Faturamento Creditos
				/*
				 * Alteração para corrigir problema de créditos persistidos na categoria errada
				 * Autor: Wellington Rocha
				 * Data: 12/09/2011*/
				this.gerarResumoFaturamentoCreditos(idSetor, anoMes, indice, qtRegistros, resumo);
				
				ResumoFaturamentoCreditosSetores listaSetores = new ResumoFaturamentoCreditosSetores(idSetor);
				if (!listaSimplificadaFaturamentoCreditosSetores.contains(listaSetores)) {

					// Resumo Faturamento Guia de Pagamento ---------------------------------------------------------
					this.gerarResumoFaturamentoGuiaPagamento(idSetor, anoMes);
					
					// Resumo Faturamento Debitos a Cobrar ----------------------------------------------------------
					this.gerarResumoFaturamentoDebitosACobrar(idSetor, anoMes);
					
					listaSimplificadaFaturamentoCreditosSetores.add(listaSetores);
				}
				
				// [SB0015 – Gerar Resumo Créditos a Realizar]
				this.gerarResumoCreditoARealizar(idSetor, idFuncionalidadeIniciada);
				
				// [SB0017 – Gerar Resumo Guias de Devolução]
				this.gerarResumoGuiasDevolucao(idSetor, idFuncionalidadeIniciada);
				
				//List resumoFaturamentoAguaEsgoto = this.repositorioGerencialFaturamento
				//.getResumoFaturamentoAguaEsgoto( idSetor, anoMes, indice, qtRegistros);
				
				if (qtRegistros > resumo.size()) {
					flagTerminou = true;
				} else {
					indice = indice + qtRegistros;
				}
				
				SistemaParametro sistemaParametro = this.getControladorUtil().pesquisarParametrosDoSistema(); 
			
				for (int i = 0; i < resumo.size(); i++) {
					
					Object obj = resumo.get(i);
		
					//System.out.println(count+" / "+i);
	
					if (obj instanceof Object[]) {
						Object[] retorno = (Object[]) obj;
						idImovelError = (Integer) retorno[01];
						
						Integer quantidadeEconomias = new Integer((Short) retorno[26]);
						if (quantidadeEconomias == null){
							quantidadeEconomias = 0;
						}
						
						
						Integer idEmpresa = (Integer) retorno[22];
						if (idEmpresa == null){
							idEmpresa = 0;
						}

						// valorAgua
						BigDecimal valorAgua = new BigDecimal(0);
						valorAgua = (BigDecimal) retorno[18];
						if (valorAgua == null) {
							valorAgua = new BigDecimal(0);
						}
						
						// valorEsgoto
						BigDecimal valorEsgoto = new BigDecimal(0); 
						valorEsgoto = (BigDecimal) retorno[19];
						if (valorEsgoto == null) {
							valorEsgoto = new BigDecimal(0);
						}

						
						// Consumo Tarifa
						Integer consumoTarifa = (Integer) retorno[23];
						if(consumoTarifa == null){
							consumoTarifa = 0;
						}
						
						Imovel imovelTemp = new Imovel();
						imovelTemp.setId((Integer) retorno[01]);
						ConsumoTarifa consumo = new ConsumoTarifa();
						consumo.setId(consumoTarifa);
						imovelTemp.setConsumoTarifa(consumo);
						
						Collection colecaoCategoriaOUSubcategoria = null;
						Integer idConta = (Integer) retorno[0];
						
						if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
			        		
			        		//[UC0108] - Quantidade de economias por categoria
							colecaoCategoriaOUSubcategoria = this.getControladorImovel()
				        	.obterQuantidadeEconomiasContaCategoriaPorSubcategoria(idConta);
			        	}
			        	else{
			        		
			        		//[UC0108] - Quantidade de economias por categoria
			        		colecaoCategoriaOUSubcategoria = this.getControladorImovel()
			        		.obterQuantidadeEconomiasContaCategoria(idConta);
			        	}
						
						Integer valorMinimo = getControladorMicromedicao().obterConsumoMinimoLigacao(imovelTemp, colecaoCategoriaOUSubcategoria);
						
						// consumoAgua
						Integer consumoAgua = 0;
						if(valorAgua.doubleValue() > 0){
							consumoAgua = (Integer) retorno[16];  
							if (consumoAgua == null) {
								consumoAgua = 0;
							}
							if(consumoAgua < valorMinimo){
								consumoAgua = valorMinimo; 
							}
						}
	
						// consumoEsgoto
						Integer consumoEsgoto = 0;
						if(valorEsgoto.doubleValue() > 0){
							consumoEsgoto = (Integer) retorno[17];
							if (consumoEsgoto == null) {
								consumoEsgoto = 0;
							}
							if(consumoEsgoto < valorMinimo){
								consumoEsgoto =  valorMinimo;
							}
						}
	
						Integer anoMesRef = (Integer) retorno[20];
						//Integer anoMesRefConta = (Integer) retorno[22];
	
						// Montamos um objeto de resumo, com as informacoes do
						// retorno
						ResumoFaturamentoAguaEsgotoHelper helper = new ResumoFaturamentoAguaEsgotoHelper(
								(Integer) retorno[1],  // Imovel 
								(Integer) retorno[2],  // Gerencia Regional
								(Integer) retorno[3],  // Unidade de negocio
								(Integer) retorno[4],  // Elo 
								(Integer) retorno[5],  // Localidade
								(Integer) retorno[6],  // Id Setor Comercial
								(Integer) retorno[7],  // id Rota
								(Integer) retorno[8],  // Id Quadra
								(Integer) retorno[9],  // Codigo do Setor Comercial
								(Integer) retorno[10], // Numero da quadra
								(Integer) retorno[11], // Perfil do imovel
								(Integer) retorno[12], // Situacao da ligacao da agua
								(Integer) retorno[13], // Situacao da ligacao do esgoto
								(Integer) retorno[14], // Perfil da ligacao do agua
								(Integer) retorno[15],// Perfil da ligacao do esgoto
								(Short)   retorno[25]); // codigo rota
						
						// Consumo Tarifa
						helper.setConsumoTarifa(consumoTarifa);
						
						// Grupo de Faturamento
						Integer grupoFaturamento = (Integer) retorno[24];
						if(grupoFaturamento == null){
							grupoFaturamento = 0;
						}
						helper.setGrupoFaturamento(grupoFaturamento);
						
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsfera( this.repositorioGerencialFaturamento.pesquisarEsferaPoderClienteResponsavelImovel( helper.getIdImovel() ) );
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoClienteResponsavel( this.repositorioGerencialFaturamento.pesquisarTipoClienteClienteResponsavelImovel( helper.getIdImovel() ) );
						// Empresa
						helper.setGempresa(idEmpresa);
						
						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do imóvel
						Integer idImovel = ( Integer )retorno[1]; // Codigo do imovel que esta sendo processado
						Categoria categoria = null;
						categoria = this.getControladorImovel()
								.obterPrincipalCategoriaImovel(idImovel);
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());
							
							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria = 
								this.getControladorImovel().obterPrincipalSubcategoria( categoria.getId(), idImovel );
							
							if ( subcategoria != null ){
								helper.setIdSubCategoria( subcategoria.getComp_id().getSubcategoria().getId() );
							}
						}
						
						// [UC0307] - Obter Indicador de Existência de Hidrômetro
						String indicadorHidrometroString = new Integer(
								getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovel)).toString();
						Short indicadorHidrometro = new Short(indicadorHidrometroString);
						// Caso indicador de hidrômetro esteja nulo
						// Seta 2(dois) = NÃO no indicador de
						// hidrômetro
						if (indicadorHidrometro == null) {
							indicadorHidrometro = new Short("2");
						}
						helper.setIndicadorHidrometro(indicadorHidrometro);
						
						// Verificamos se a esfera de poder foi encontrada
						// [FS0002] Verificar existencia de cliente responsavel
						if ( helper.getIdEsfera().equals( 0 ) ){
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
							if ( clienteTemp != null ){
							  helper.setIdEsfera( clienteTemp.getClienteTipo().getEsferaPoder().getId() );
							}
						}	
						if ( helper.getIdTipoClienteResponsavel().equals( 0 ) ){
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
							if ( clienteTemp != null ){
							  helper.setIdTipoClienteResponsavel( clienteTemp.getClienteTipo().getId() );
							}
						}
						
						// se ja existe um objeto igual a ele entao soma os
						// valores e as quantidades ja existentes.
						// um objeto eh igual ao outro se ele tem todos as
						// informacos iguals 
						if (listaSimplificadaFaturamentoAguaEsgoto.contains(helper)) {
							int posicao = listaSimplificadaFaturamentoAguaEsgoto.indexOf(helper);
							ResumoFaturamentoAguaEsgotoHelper jaCadastrado = 
								(ResumoFaturamentoAguaEsgotoHelper) listaSimplificadaFaturamentoAguaEsgoto.get(posicao);
							// Somatorios
	
							// Consumo Água
							jaCadastrado.setConsumoAgua( 
									jaCadastrado.getConsumoAgua() + consumoAgua);
	
							// Consumo Esgoto
							jaCadastrado.setConsumoEsgoto( 
									jaCadastrado.getConsumoEsgoto() + consumoEsgoto);
	
							// Valor Agua
							jaCadastrado.setValorAgua( 
									jaCadastrado.getValorAgua().add(valorAgua));
	
							// Valor Agua
							jaCadastrado.setValorEsgoto( 
									jaCadastrado.getValorEsgoto().add(valorEsgoto));
	
							// Quantidade de Contas
							jaCadastrado.setQuantidadeFaturamento( 
									jaCadastrado.getQuantidadeFaturamento()+1);

							// Quantidade de Economias
							jaCadastrado.setQuantidadeEconomias( 
									jaCadastrado.getQuantidadeEconomias() + quantidadeEconomias);
							
							
							// AnoMesReferencia
							jaCadastrado.setAnoMesReferencia(jaCadastrado.getAnoMesReferencia());
						
						} else {
							// Somatorios
							
							// Consumo Agua
							helper.setConsumoAgua(consumoAgua); //helper.getConsumoAgua().intValue() +
	
							// Consumo Esgoto
							helper.setConsumoEsgoto(consumoEsgoto);
							
							// Valor Agua
							helper.setValorAgua(valorAgua); // helper.getValorAgua().add(valorAgua));
	
							// Valor Agua
							helper.setValorEsgoto(valorEsgoto); //helper.getValorEsgoto().add());
	
							// Quantidade Faturamento
							helper.setQuantidadeFaturamento(1); //helper.getQuantidadeFaturamento()+1);
							
							//Quantidade Faturamento
							helper.setQuantidadeEconomias(quantidadeEconomias); //helper.getQuantidadeFaturamento()+1);
							
							
							// AnoMesReferencia
							helper.setAnoMesReferencia(anoMesRef);
							
	
							listaSimplificadaFaturamentoAguaEsgoto.add(helper);
						}
					}
				}
			}// do while
			/**
			 * para todas os ResumoFaturamentoAguaEsgotoHelper cria
			 * ResumoFaturamentoAguaEsgoto
			 */
			// for lista simplificada
			System.out.println("inicio inserindo dados");
			System.out.println("======================================================================================");
			
			for (int i = 0; i < listaSimplificadaFaturamentoAguaEsgoto.size(); i++) {
				ResumoFaturamentoAguaEsgotoHelper helper = (ResumoFaturamentoAguaEsgotoHelper) listaSimplificadaFaturamentoAguaEsgoto
						.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Volume Faturado Agua
				Integer volumeFaturadoAgua = helper.getConsumoAgua();
				if(volumeFaturadoAgua == null){
					volumeFaturadoAgua = 0;
				}
				//	Volume Faturado Esgoto
				Integer volumeFaturadoEsgoto = helper.getConsumoEsgoto();
				if(volumeFaturadoEsgoto == null){
					volumeFaturadoEsgoto = 0;
				}
				// Valor Faturado Agua
				BigDecimal valorFaturadoAgua = helper.getValorAgua();
				if(valorFaturadoAgua == null){
					valorFaturadoAgua = new BigDecimal(0);
				}
//				 Valor Faturado Esgoto
				BigDecimal valorFaturadoEsgoto = helper.getValorEsgoto();
				if(valorFaturadoEsgoto == null){
					valorFaturadoEsgoto = new BigDecimal(0);
				}
				// quantidade de contas emitidas
				Integer quantidadeFaturamento = helper.getQuantidadeFaturamento();

				// quantidade de Economias
				Integer quantidadeEconomias = helper.getQuantidadeEconomias();
				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// id Conta
				//Integer idConta = helper.getIdConta();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	
				
				// Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				// Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}

				// Tipo Documento
				GDocumentoTipo gerDocumentoTipo = new GDocumentoTipo();
				gerDocumentoTipo.setId(1);
				
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				// Criamos um resumo do FaturamentoAguaEsgoto 
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, volumeFaturadoAgua, 
						volumeFaturadoEsgoto		, valorFaturadoAgua			, valorFaturadoEsgoto,
						quantidadeFaturamento		, quantidadeEconomias 		, ultimaAlteracao, 
						elo							, rota						, empresa, 
						gerConsumoTarifa 			, gerFaturamentoGrupo		, indicadorHidrometro,
						new BigDecimal(0), gerDocumentoTipo, helper.getCodigoRota() );

				//	Adicionamos na tabela ResumoFaturamentoOutros
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);				
				//listaResumoFaturamentoAguaEsgoto.add(resumoFaturamentoAguaEsgotoGrava);
			}// do for lista simplificada
			System.out.println("======================================================================================");
			System.out.println("final montagem dos dados");
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
		} catch (Exception ex) {
			System.out.println("IMOVEL COM ERRO: " + idImovelError);
			ex.printStackTrace();
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO AGUA ESGOTO", ex);
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	// ReFaturamento
	/**
	 * Método que gera o resumo do ReFaturamento
	 * 
	 * [UC0715] - Gerar Resumo do ReFaturamento
	 * 
	 * @author Roberto Barbalho
	 * @param idSetor 
	 * @param anoMes 
	 * @date 12/11/2007
	 * 
	 */
	public void gerarResumoReFaturamento(int idSetor,
			int idFuncionalidadeIniciada, int anoMes) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
		
		try {
			// Listas de Controle
			List<ResumoReFaturamentoHelper> listaSimplificadaReFaturamento = new ArrayList();
			List<UnResumoRefaturamento> listaResumoRefaturamento = new ArrayList();
			// Indices da paginacao
			int indice = 0;
			int qtRegistros = 500;
			// flag da paginacao 
			boolean flagTerminou = false;
			// contador de paginacao(informativo no debug)
			int count = 0;

			//FS0001 - Verificar existencia de dados para o ano/mes referencia informado
	   		getControladorGerencialCadastro().excluirResumoGerencial( 
	   				getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
	   				UnResumoRefaturamento.class.getName(), "anoMesReferencia", idSetor );				
			
			// inicio do processamento
			while(!flagTerminou) {
				
				count++;
				List resumoRefaturamento = this.repositorioGerencialFaturamento
				.getResumoReFaturamento(idSetor, anoMes, indice, qtRegistros);
				
				// Resumo Faturamento Guia de Pagamento ---------------------------------------------------------
				this.gerarResumoReFaturamentoGuiaPagamento(idSetor, anoMes);
				
				if (qtRegistros > resumoRefaturamento.size()) {
					flagTerminou = true;
				} else {
					indice = indice + qtRegistros;
				}

				System.out.println("processando resumo refaturamento para o setor:"+idSetor);
				
				for (int i = 0; i < resumoRefaturamento .size(); i++) {
					
					int qtdreg = i + 1;
					
					Object obj = resumoRefaturamento .get(i);

					System.out.println("registro: "+qtdreg+" / "+resumoRefaturamento .size());
	
					if (obj instanceof Object[]) {
						Object[] retorno = (Object[]) obj;
						
						Integer tipoConta = (Integer) retorno[21];
						
						Integer tipoContaAnt = (Integer) retorno[26];
						
						
						if (tipoContaAnt == null) {
							tipoContaAnt = 9999;
						}
						
						
						// consumoAgua
						Integer consumoAgua = (Integer) retorno[16];  
						if (consumoAgua == null) {
							consumoAgua = 0;
						}
	
						// consumoEsgoto
						Integer consumoEsgoto = (Integer) retorno[17];
						if (consumoEsgoto == null) {
							consumoEsgoto = 0;
						}
	
						// valorAgua
						
						
						BigDecimal valorAgua = (BigDecimal) retorno[18];
						if (valorAgua == null) {
							valorAgua = new BigDecimal(0);
						}
						
						// valorEsgoto
						BigDecimal valorEsgoto = (BigDecimal) retorno[19];
						if (valorEsgoto == null) {
							valorEsgoto = new BigDecimal(0);
						}
						
						Integer anoMesRefConta = (Integer) retorno[20];
						Integer anoMesRef = (Integer) retorno[22];

						// Impostos
						BigDecimal valorImpostos = (BigDecimal) retorno[23];  
						if (valorImpostos == null) {
							valorImpostos = new BigDecimal(0);
						}
						
						// Creditos
						BigDecimal valorCreditos = (BigDecimal) retorno[24];  
						if (valorCreditos == null) {
							valorCreditos = new BigDecimal(0);
						}
						
						// Debitos
						BigDecimal valorDebitos = (BigDecimal) retorno[25];  
						if (valorDebitos == null) {
							valorDebitos = new BigDecimal(0);
						}
						
						
						// Montamos um objeto de resumo, com as informacoes do
						// retorno
						ResumoReFaturamentoHelper helper = new ResumoReFaturamentoHelper(
								(Integer) retorno[2],  // Gerencia Regional
								(Integer) retorno[3],  // Unidade de negocio
								(Integer) retorno[4],  // Elo 
								(Integer) retorno[5],  // Localidade
								(Integer) retorno[6],  // Id Setor Comercial
								(Integer) retorno[7],  // id Rota
								(Integer) retorno[8],  // Id Quadra
								(Integer) retorno[9],  // Codigo do Setor Comercial
								(Integer) retorno[10], // Numero da quadra
								(Integer) retorno[11], // Perfil do imovel
								(Integer) retorno[12], // Situacao da ligacao da agua
								(Integer) retorno[13], // Situacao da ligacao do esgoto
								(Integer) retorno[14], // Perfil da ligacao do agua
								(Integer) retorno[15]);// Perfil da ligacao do esgoto

						
						// AnoMesReferencia
						
						helper.setAnoMesReferencia(anoMesRef);
						helper.setAnoMesReferenciaConta(anoMesRefConta);
						
						// Pesquisamos a esfera de poder do cliente responsavel
						helper.setIdEsfera( this.repositorioGerencialFaturamento.pesquisarEsferaPoderClienteResponsavelImovel( (Integer) retorno[0] ) );
						// Pesquisamos o tipo de cliente responsavel do imovel
						helper.setIdTipoClienteResponsavel( this.repositorioGerencialFaturamento.pesquisarTipoClienteClienteResponsavelImovel( (Integer) retorno[0] ) );
						
						// pesquisando a categoria
						// [UC0306] - Obtter principal categoria do imóvel
						Integer idImovel = ( Integer )retorno[1]; // Codigo do imovel que esta sendo processado
						Categoria categoria = null;
						categoria = this.getControladorImovel()
								.obterPrincipalCategoriaImovel(idImovel);
						if (categoria != null) {
							helper.setIdCategoria(categoria.getId());
							
							// Pesquisando a principal subcategoria
							ImovelSubcategoria subcategoria = 
								this.getControladorImovel().obterPrincipalSubcategoria( categoria.getId(), idImovel );
							
							if ( subcategoria != null ){
								helper.setIdSubCategoria( subcategoria.getComp_id().getSubcategoria().getId() );
							}
						}
						
						// Verificamos se a esfera de poder foi encontrada
						// [FS0002] Verificar existencia de cliente responsavel
						if ( helper.getIdEsfera().equals( 0 ) ){
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
							if ( clienteTemp != null ){
							  helper.setIdEsfera( clienteTemp.getClienteTipo().getEsferaPoder().getId() );
							}
						}	
						if ( helper.getIdTipoClienteResponsavel().equals( 0 ) ){
							Imovel imovel = new Imovel();
							imovel.setId(idImovel);
							Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
							if ( clienteTemp != null ){
							  helper.setIdTipoClienteResponsavel( clienteTemp.getClienteTipo().getId() );
							}
						}

						
						// se ja existe um objeto igual a ele entao soma os
						// valores e as quantidades ja existentes.
						// um objeto eh igual ao outro se ele tem todos as
						// informacos iguals 
						if (listaSimplificadaReFaturamento.contains(helper)) {

							int posicao = listaSimplificadaReFaturamento.indexOf(helper);
							ResumoReFaturamentoHelper jaCadastrado = 
								(ResumoReFaturamentoHelper) listaSimplificadaReFaturamento.get(posicao);
							// Somatorios
							
							// Retificadas

							if (tipoConta == 1 || tipoContaAnt == 1) {
								
								jaCadastrado.setQtContasRetificadas(jaCadastrado.getQtContasRetificadas()+1 );
								
								List valoresContaRetificada = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22], 0  );

								for (int r = 0; r < valoresContaRetificada .size(); r++) {
									
									Object objr = valoresContaRetificada .get(r);

									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										

										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}

										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}

										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}

										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
										
										if ( valorDifAgua.floatValue() > 0){
											jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorDifAgua)) ;
									
										} else if (valorDifAgua.floatValue() < 0) {
											jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add( valorDifAgua.negate() )) ;
										}
									
								
										if ( valorDifEsgoto.floatValue() > 0){
											jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add( valorDifEsgoto)) ;
									
										} else if (valorDifEsgoto.floatValue() < 0) {
											jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add( valorDifEsgoto.negate())) ;
										}

        						
										if ( valorDifDebitos.floatValue() > 0){
											jaCadastrado.setVlCanceladoDebitos(jaCadastrado.getVlCanceladoDebitos().add( valorDifDebitos)) ;
									
										} else if (valorDifDebitos.floatValue() < 0) {
											jaCadastrado.setVlIncluidoDebitos(jaCadastrado.getVlIncluidoDebitos().add( valorDifDebitos.negate())); 
										}	

										if ( valorDifCreditos.floatValue() > 0){
											jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add( valorDifCreditos)) ;
									
										} else if (valorDifCreditos.floatValue() < 0) {
											jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add( valorDifCreditos.negate())) ;
										}

										if ( valorDifImpostos.floatValue() > 0){
											jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add( valorDifImpostos)) ;
											
										} else if (valorDifImpostos.floatValue() < 0) {
											jaCadastrado.setVlIncluidoImpostos(jaCadastrado.getVlIncluidoImpostos().add( valorDifImpostos.negate())) ;
										}

										if (valorDifVolAgua > 0) {
											jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() + valorDifVolAgua ) ;
											
										} else if (valorDifVolAgua < 0) {
											jaCadastrado.setVoIncludoAgua( jaCadastrado.getVoIncludoAgua()  +  ((valorDifVolAgua) * (-1)) ) ;
										}

										if (valorDifVolEsgoto > 0) {
											jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() + valorDifVolEsgoto ) ;
											
										} else if (valorDifVolEsgoto < 0) {
											jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() +  ((valorDifVolEsgoto) * (-1))) ;
										}
										
										
									}
									
								}
								
								List valoresContaRetificadasem4 = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22], 2  );

								for (int r = 0; r < valoresContaRetificadasem4 .size(); r++) {
									
									Object objr = valoresContaRetificadasem4 .get(r);

									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										

										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}

										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}

										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}

										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
 									    jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add(valorDifAgua)) ;
									
										jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add( valorDifEsgoto)) ;

										jaCadastrado.setVlIncluidoDebitos(jaCadastrado.getVlIncluidoDebitos().add( valorDifDebitos)) ;
									
										jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add( valorDifCreditos)) ;
									
										jaCadastrado.setVlIncluidoImpostos(jaCadastrado.getVlIncluidoImpostos().add( valorDifImpostos)) ;
											
										jaCadastrado.setVoIncludoAgua(jaCadastrado.getVoIncludoAgua() + valorDifVolAgua ) ;
											
										jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() + valorDifVolEsgoto ) ;
											
												
									}
									
								}

								
								
								
										
							}
        						
							// Incluidas
							if(tipoConta == 2){
								jaCadastrado.setQtContasIncluidas(jaCadastrado.getQtContasIncluidas()+1);
								jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add(valorAgua));
								jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add(valorEsgoto));
								jaCadastrado.setVlIncluidoDebitos(jaCadastrado.getVlIncluidoDebitos().add(valorDebitos));
								jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add(valorCreditos));
								jaCadastrado.setVlIncluidoImpostos(jaCadastrado.getVlIncluidoImpostos().add(valorImpostos));
								jaCadastrado.setVoIncludoAgua(jaCadastrado.getVoIncludoAgua() +consumoAgua);
								jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() +consumoEsgoto);
							}
							// Canceladas
							if(tipoConta == 3){
								jaCadastrado.setQtContasCanceladas(jaCadastrado.getQtContasCanceladas()+1);
								jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorAgua));
								jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add(valorEsgoto));
								jaCadastrado.setVlCanceladoDebitos(jaCadastrado.getVlCanceladoDebitos().add(valorDebitos));
								jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditos));
								jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(valorImpostos));
								jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() +consumoAgua);
								jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() +consumoEsgoto);
							}

							// Canceladas por Refaturamento sem a conta correspondente com o dcst_idatual = 1 ou dcst_idanterior = 1

							if (tipoConta == 4) {
								
								List valoresContaRetificada = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22], 1  );

								for (int r = 0; r < valoresContaRetificada .size(); r++) {
									
									Object objr = valoresContaRetificada .get(r);

									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										

										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}

										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}

										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}

										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
 									    jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorDifAgua)) ;
									
										jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add( valorDifEsgoto)) ;

										jaCadastrado.setVlCanceladoDebitos(jaCadastrado.getVlCanceladoDebitos().add( valorDifDebitos)) ;
									
										jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add( valorDifCreditos)) ;
									
										jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add( valorDifImpostos)) ;
											
										jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() + valorDifVolAgua ) ;
											
										jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() + valorDifVolEsgoto ) ;
											
												
									}
									
								}
										
							}

							
							
							// AnoMesReferencia

							jaCadastrado.setAnoMesReferencia(jaCadastrado.getAnoMesReferencia());
							jaCadastrado.setAnoMesReferenciaConta(jaCadastrado.getAnoMesReferenciaConta());
							

						} else {
							
	    					// Somatorios    
							
							
								if (tipoConta == 1 || tipoContaAnt == 1 ) {
									
								
								// Retificada
								
								helper.setQtContasRetificadas(1);
	
								
								List valoresContaRetificada = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22] , 0 );
	
								for (int r = 0; r < valoresContaRetificada .size(); r++) {
									
									Object objr = valoresContaRetificada .get(r);
	
									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										
	
										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}
	
										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}
	
										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}
	
										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
										
										if ( valorDifAgua.floatValue() > 0){
											helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorDifAgua)) ;
									
										} else if (valorDifAgua.floatValue() < 0) {
											helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add( valorDifAgua.negate() )) ;
										}
									
								
										if ( valorDifEsgoto.floatValue() > 0){
											helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add( valorDifEsgoto)) ;
									
										} else if (valorDifEsgoto.floatValue() < 0) {
											helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add( valorDifEsgoto.negate())) ;
										}
	
	    						
										if ( valorDifDebitos.floatValue() > 0){
											helper.setVlCanceladoDebitos(helper.getVlCanceladoDebitos().add( valorDifDebitos)) ;
									
										} else if (valorDifDebitos.floatValue() < 0) {
											helper.setVlIncluidoDebitos(helper.getVlIncluidoDebitos().add( valorDifDebitos.negate())); 
										}	
	
										if ( valorDifCreditos.floatValue() > 0){
											helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add( valorDifCreditos)) ;
									
										} else if (valorDifCreditos.floatValue() < 0) {
											helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add( valorDifCreditos.negate())) ;
										}
	
										if ( valorDifImpostos.floatValue() > 0){
											helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add( valorDifImpostos)) ;
											
										} else if (valorDifImpostos.floatValue() < 0) {
											helper.setVlIncluidoImpostos(helper.getVlIncluidoImpostos().add( valorDifImpostos.negate())) ;
										}
	
										if (valorDifVolAgua > 0) {
											helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() + valorDifVolAgua ) ;
											
										} else if (valorDifVolAgua < 0) {
											helper.setVoIncludoAgua( helper.getVoIncludoAgua()  +  (valorDifVolAgua * (-1))) ;
										}
	
										if (valorDifVolEsgoto > 0) {
											helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() + valorDifVolEsgoto ) ;
											
										} else if (valorDifVolEsgoto < 0) {
											helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() +  (valorDifVolEsgoto * (-1)) ) ;
										}
										
										
									}
									
								}
								
								List valoresContaRetificadasem4 = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22], 2  );

								for (int r = 0; r < valoresContaRetificadasem4 .size(); r++) {
									
									Object objr = valoresContaRetificadasem4 .get(r);

									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										

										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}

										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}

										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}

										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
 									    helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add(valorDifAgua)) ;
									
 									    helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add( valorDifEsgoto)) ;

 									    helper.setVlIncluidoDebitos(helper.getVlIncluidoDebitos().add( valorDifDebitos)) ;
									
 									    helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add( valorDifCreditos)) ;
									
 									    helper.setVlIncluidoImpostos(helper.getVlIncluidoImpostos().add( valorDifImpostos)) ;
											
 									    helper.setVoIncludoAgua(helper.getVoIncludoAgua() + valorDifVolAgua ) ;
											
 									    helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() + valorDifVolEsgoto ) ;
											
												
									}
									
								}
								
							
							}
    						
							// Incluidas
							if(tipoConta == 2){
								helper.setQtContasIncluidas(helper.getQtContasIncluidas()+1);
								helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add(valorAgua));
								helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add(valorEsgoto));
								helper.setVlIncluidoDebitos(helper.getVlIncluidoDebitos().add(valorDebitos));
								helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add(valorCreditos));
								helper.setVlIncluidoImpostos(helper.getVlIncluidoImpostos().add(valorImpostos));
								helper.setVoIncludoAgua(helper.getVoIncludoAgua() +consumoAgua);
								helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() +consumoEsgoto);
							}
							// Canceladas
							if(tipoConta == 3){
								helper.setQtContasCanceladas(helper.getQtContasCanceladas()+1);
								helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorAgua));
								helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add(valorEsgoto));
								helper.setVlCanceladoDebitos(helper.getVlCanceladoDebitos().add(valorDebitos));
								helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditos));
								helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(valorImpostos));
								helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() +consumoAgua);
								helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() +consumoEsgoto);
							}
							
							// Canceladas por Refaturamento sem a conta correspondente com o (dcst_idatual = 1 ou dcst_idanterior = 1)
							if (tipoConta == 4) {
								
								List valoresContaRetificada = this.repositorioGerencialFaturamento
								.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[20], (Integer) retorno[22], 1  );
								

								for (int r = 0; r < valoresContaRetificada .size(); r++) {
									
									Object objr = valoresContaRetificada .get(r);

									if (objr instanceof Object[]) {
										Object[] retornor = (Object[]) objr;
										

										// valorAgua
										BigDecimal valorDifAgua = (BigDecimal) retornor[0];
										if (valorDifAgua == null) {
											valorDifAgua = new BigDecimal(0);
										}
										
										// valorEsgoto
										BigDecimal valorDifEsgoto = (BigDecimal) retornor[1];
										if (valorDifEsgoto == null) {
											valorDifEsgoto = new BigDecimal(0);
										}
										
										// Debitos
										BigDecimal valorDifDebitos = (BigDecimal) retornor[2];  
										if (valorDifDebitos == null) {
											valorDifDebitos = new BigDecimal(0);
										}

										// Creditos
										BigDecimal valorDifCreditos = (BigDecimal) retornor[3];  
										if (valorDifCreditos == null) {
											valorDifCreditos = new BigDecimal(0);
										}

										// Impostos
										BigDecimal valorDifImpostos = (BigDecimal) retornor[4];  
										if (valorDifImpostos == null) {
											valorDifImpostos = new BigDecimal(0);
										}
										
										// Volume Agua
										Integer valorDifVolAgua = (Integer) retornor[5];  
										if (valorDifVolAgua == null) {
											valorDifVolAgua = new Integer(0);
										}

										// Volume Esgoto
										Integer valorDifVolEsgoto = (Integer) retornor[6];  
										if (valorDifVolEsgoto == null) {
											valorDifVolEsgoto = new Integer(0);
										}
										
  									    helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorDifAgua)) ;
									
  									    helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add( valorDifEsgoto)) ;
									
										helper.setVlCanceladoDebitos(helper.getVlCanceladoDebitos().add( valorDifDebitos)) ;
									
										helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add( valorDifCreditos)) ;
									
										helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add( valorDifImpostos)) ;
										
										helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() + valorDifVolAgua ) ;
										
										helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() + valorDifVolEsgoto ) ;
												
									}
									
								}
										
							}
							
							
							// AnoMesReferencia

							helper.setAnoMesReferencia(helper.getAnoMesReferencia());
							helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
							listaSimplificadaReFaturamento.add(helper); 
						}

	    						
	    						// } catch (Exception e) {
						// e.printStackTrace();
						// }
	
					}
				}
			} //do while
			/**
			 * para todos os ResumoRefaturamentoHelper cria
			 * ResumoRefaturamento
			 */
		    // for lista simplificada

			if (listaSimplificadaReFaturamento.size() > 0) {
			System.out.println("inicio inserindo dados");
			}
			
			
			for (int i = 0; i < listaSimplificadaReFaturamento.size(); i++) {
				ResumoReFaturamentoHelper helper = (ResumoReFaturamentoHelper) listaSimplificadaReFaturamento
						.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();

				Integer anoMesReferenciaConta = helper.getAnoMesReferenciaConta();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null)	 {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
				}				
				
				Integer qtContasRetificadasInserir = helper.getQtContasRetificadas(); 
				Integer qtContasCanceladasInserir  = helper.getQtContasCanceladas(); 
				BigDecimal vlCanceladoAguaInserir = helper.getVlCanceladoAgua(); 
				BigDecimal vlCanceladoEsgotoInserir = helper.getVlCanceladoEsgoto(); 
				Integer voCanceladoAguaInserir = helper.getVoCanceladoAgua(); 
				Integer voCanceladoEsgotoInserir = helper.getVoCanceladoEsgoto(); 
				Integer qtContasIncluidasInserir   = helper.getQtContasIncluidas();
				BigDecimal vlIncluidasAguaInserir = helper.getVlIncluidoAgua(); 
				BigDecimal vlIncluidasEsgotoInserir = helper.getVlIncluidoEsgoto(); 
				Integer voIncluidasAguaInserir = helper.getVoIncludoAgua();
				Integer voIncluidasEsgotoInserir = helper.getVoIncluidoEsgoto();
				BigDecimal vlIncluidoDebitosInserir = helper.getVlIncluidoDebitos(); 
				BigDecimal vlCanceladoDebitosInserir = helper.getVlCanceladoDebitos(); 
				BigDecimal vlIncluidoCreditosInserir = helper.getVlIncluidoCreditos(); 
				BigDecimal vlCanceladoCreditosInserir = helper.getVlCanceladoCreditos(); 
				BigDecimal vlIncluidoImpostosInserir = helper.getVlIncluidoImpostos(); 
				BigDecimal vlCanceladoImpostosInserir = helper.getVlCanceladoImpostos(); 
				BigDecimal vlIncluidoGuiasInserir = helper.getVlIncluidoGuias(); 
				BigDecimal vlCanceladoGuiasInserir = helper.getVlCanceladoGuias(); 
				Integer qtGuiasIncluidasInserir = helper.getQtGuiasIncluidas(); 
				Integer qtGuiasCanceladasInserir  = helper.getQtGuiasCanceladas(); 
				

				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Criamos um resumo do Refaturamento
				UnResumoRefaturamento resumoReFaturamentoGrava = new UnResumoRefaturamento( 
						anoMesReferencia			, anoMesReferenciaConta     , gerenciaRegional			, 
						unidadeNegocio              , localidade				, setorComercial			, 
						quadra                      , codigoSetorComercial      , numeroQuadra , 
						imovelPerfil                , esferaPoder				, clienteTipo				, 
						ligacaoAguaSituacao         , ligacaoEsgotoSituacao		, categoria					, 
						subcategoria                , perfilLigacaoAgua			, perfilLigacaoEsgoto		, 
						qtContasRetificadasInserir  , 
						qtContasCanceladasInserir	, vlCanceladoAguaInserir	, vlCanceladoEsgotoInserir,
						voCanceladoAguaInserir		, voCanceladoEsgotoInserir	, qtContasIncluidasInserir,		
						vlIncluidasAguaInserir		, vlIncluidasEsgotoInserir	, voIncluidasAguaInserir, 
						voIncluidasEsgotoInserir	, ultimaAlteracao			, elo,
						rota, vlIncluidoImpostosInserir, vlCanceladoImpostosInserir, vlIncluidoGuiasInserir, vlCanceladoGuiasInserir, 
						qtGuiasIncluidasInserir,  vlIncluidoCreditosInserir, vlIncluidoDebitosInserir, qtGuiasCanceladasInserir, 
						vlCanceladoCreditosInserir,vlCanceladoDebitosInserir );


			this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoReFaturamentoGrava);				
				
				listaResumoRefaturamento.add(resumoReFaturamentoGrava);

			}// do for lista simplificada
			
			if (listaSimplificadaReFaturamento.size() > 0 ) {
			System.out.println("final gravação dos dados");
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);

		} catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO REFATURAMENTO", ex);
			sessionContext.setRollbackOnly();
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
			
		
		
	/**  gerarResumoFaturamentoOutros
	 * Marcio Roberto - 03/07/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoFaturamentoOutros(int idSetor, int anoMes,
			int indice, int qtRegistros, List resumo) throws ControladorException, ErroRepositorioException {
		
		Integer idImovelError = 0;
		try {
			
			System.out.println("processando resumo faturamento outros ");	
	
			List<ResumoFaturamentoOutrosHelper> listaSimplificadaFaturamentoOutros = new ArrayList();
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			
			List resumoContasFaturamentoAguaEsgoto = resumo; 
				//this.repositorioGerencialFaturamento
			//.getContasResumoFaturamentoAguaEsgoto( idSetor, anoMes, indice, qtRegistros);				
			
			for (int c = 0; c < resumoContasFaturamentoAguaEsgoto.size(); c++) {
				Object obj = resumoContasFaturamentoAguaEsgoto.get(c);
				Integer idContaResumo = 0;
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					idContaResumo = (Integer) retorno[0];
					
					Integer idImovelOutros = (Integer) retorno[1];
					idImovelError = idImovelOutros;
	
					Integer idEmpresaOutros = (Integer) retorno[22];
	
					// ResumoFaturamentoOutros
					List resumoDebitoCobrado = this.repositorioGerencialFaturamento
							.getPesquisaDebitoCobrado(idContaResumo,idImovelOutros, anoMes );
	
					for (int y = 0; y < resumoDebitoCobrado.size(); y++) {
						Object objOutros = resumoDebitoCobrado.get(y);
						if (obj instanceof Object[]) {
							Object[] retornoOutros = (Object[]) objOutros;
							ResumoFaturamentoOutrosHelper helperOutros = new ResumoFaturamentoOutrosHelper(
									(Integer) retorno[1], // Imovel
									(Integer) retorno[2], // Gerencia Regional
									(Integer) retorno[3], // Unidade de negocio
									(Integer) retorno[4], // Elo
									(Integer) retorno[5], // Localidade
									(Integer) retorno[6], // Id Setor Comercial
									(Integer) retorno[7], // id Rota
									(Integer) retorno[8], // Id Quadra
									(Integer) retorno[9], // Codigo do Setor Comercial
									(Integer) retorno[10], // Numero da quadra
									(Integer) retorno[11], // Perfil do imovel
									(Integer) retorno[12], // Situacao da ligacao da agua
									(Integer) retorno[13], // Situacao da ligacao do esgoto
									(Integer) retorno[14], // Perfil da ligacao do agua
									(Integer) retorno[15],// Perfil da ligacao do esgoto
									(Short)   retorno[25]);// codigo Rota
							
							Integer anoMesReferencia = (Integer) retorno[20];
							
							
							//	Consumo Tarifa.
							Integer consumoTarifa = (Integer) retorno[23];
							if(consumoTarifa == null){
								consumoTarifa = 0;
							}
							helperOutros.setConsumoTarifa(consumoTarifa);
							
							//	Grupo de Faturamento
							Integer grupoFaturamento = (Integer) retorno[24];
							if(grupoFaturamento == null){
								grupoFaturamento = 0;
							}
							helperOutros.setGrupoFaturamento(grupoFaturamento);
	
							// Pesquisamos a esfera de poder do cliente
							helperOutros.setIdEsfera(this.repositorioGerencialFaturamento
											.pesquisarEsferaPoderClienteResponsavelImovel(helperOutros
													.getIdImovel()));
							// Pesquisamos o tipo de cliente responsavel do
							helperOutros.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
											.pesquisarTipoClienteClienteResponsavelImovel(helperOutros
													.getIdImovel()));
							// Empresa
							helperOutros.setGempresa(idEmpresaOutros);
							// Categoria
							Categoria categoriaOutros = null;
							categoriaOutros = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovelOutros);
							if (categoriaOutros != null) {
								helperOutros.setIdCategoria(categoriaOutros.getId());
								// Pesquisando a principal subcategoria
								ImovelSubcategoria subcategoriaOutros = this.getControladorImovel()
										.obterPrincipalSubcategoria(categoriaOutros.getId(),idImovelOutros);
								if (subcategoriaOutros != null) {
									helperOutros.setIdSubCategoria(subcategoriaOutros.getComp_id().getSubcategoria().getId());
								}
							}
							// Verificamos se a esfera de poder foi encontrada
							if (helperOutros.getIdEsfera().equals(0)) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelOutros);
								Cliente clienteTempOutros = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
								if (clienteTempOutros != null) {
									helperOutros.setIdEsfera(clienteTempOutros.getClienteTipo().getEsferaPoder().getId());
								}
							}
							// Verificar existencia de cliente responsavel
							if (helperOutros.getIdTipoClienteResponsavel().equals(0)) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelOutros);
								Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
								if (clienteTemp != null) {
									helperOutros.setIdTipoClienteResponsavel(clienteTemp.getClienteTipo().getId());
								}
							}
							
							//	[UC0307] - Obter Indicador de Existência de Hidrômetro
							String indicadorHidrometroString = new Integer(
									getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovelOutros)).toString();
							Short indicadorHidrometro = new Short(indicadorHidrometroString);
							// Caso indicador de hidrômetro esteja nulo
							// Seta 2(dois) = NÃO no indicador de
							// hidrômetro
							if (indicadorHidrometro == null) {
								indicadorHidrometro = new Short("2");
							}
							helperOutros.setIndicadorHidrometro(indicadorHidrometro);							
							
							// Campos referentes ao "resumoDebitoCobrado" ///////////////////////////////////////
							Integer financiamentoTipo = (Integer) retornoOutros[0];
							if (financiamentoTipo == null) {
								financiamentoTipo = 0;
							}
							helperOutros.setIdFinanciamentoTipo(financiamentoTipo);
	
							Integer documentoTipo = (Integer) retornoOutros[1];
							if (documentoTipo == null) {
								documentoTipo = 0;
							}
							helperOutros.setIdDocumentoTipo(documentoTipo);

							Integer lictId = (Integer) retornoOutros[2];
							if (lictId == null) {
								lictId = 0;
							}
							helperOutros.setLictId(lictId);
							
							// Valor dos Documentos Faturados
							BigDecimal valorDocumentosFaturados = (BigDecimal) retornoOutros[3];
							if (valorDocumentosFaturados == null) {
								valorDocumentosFaturados = new BigDecimal(0);
							}
							// Quantidade dos Documentos Faturados
							Integer quantidadeDocumentosFaturados = (Integer) retornoOutros[4];
							if (quantidadeDocumentosFaturados == null) {
								quantidadeDocumentosFaturados = 0;
							}
							/////////////////////////////////////////////////////////////////////////////////////
							// informacos iguals 
							if (listaSimplificadaFaturamentoOutros.contains(helperOutros)) {
								int posicaoOutros = listaSimplificadaFaturamentoOutros.indexOf(helperOutros);
								ResumoFaturamentoOutrosHelper jaCadastradoOutros = 
									(ResumoFaturamentoOutrosHelper) listaSimplificadaFaturamentoOutros.get(posicaoOutros);
								// Somatorios
		
								// documentos faturados
								jaCadastradoOutros.setQuantidadeDocumentosFaturados( 
										jaCadastradoOutros.getQuantidadeDocumentosFaturados() + quantidadeDocumentosFaturados);
		
								// Valor Documentos faturados
								jaCadastradoOutros.setValorDocumentosFaturados( 
										jaCadastradoOutros.getValorDocumentosFaturados().add(valorDocumentosFaturados));
							} else {
								// Somatorios
								
								// documentos faturados
								helperOutros.setQuantidadeDocumentosFaturados(quantidadeDocumentosFaturados); 
		
								// Valor Documentos faturados
								helperOutros.setValorDocumentosFaturados(valorDocumentosFaturados);
								
								helperOutros.setAnoMesReferencia(anoMesReferencia);
								
								listaSimplificadaFaturamentoOutros.add(helperOutros);
							}
						}// if instance of de outros
					}// for de outros
				}// if instance of de contas
			}// for de contas
			
			for (int i = 0; i < listaSimplificadaFaturamentoOutros.size(); i++) {
				ResumoFaturamentoOutrosHelper helper = (ResumoFaturamentoOutrosHelper) listaSimplificadaFaturamentoOutros.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	

				// Valor dos Documentos Faturados
				BigDecimal valorDocumentosFaturados = (BigDecimal) helper.getValorDocumentosFaturados();

				// Quantidade dos Documentos Faturados
				Integer quantidadeDocumentosFaturados = helper.getQuantidadeDocumentosFaturados();
				
				Integer financiamentoTipo = helper.getIdFinanciamentoTipo();
				Integer lictId = helper.getLictId();
				Integer tipoDocumento = helper.getIdDocumentoTipo(); //   DocumentoTipo.CONTA;
				
				// Financiamento Tipo
				GFinanciamentoTipo gerFinanciamentoTipo = null;
				if(financiamentoTipo != null ){
					gerFinanciamentoTipo = new GFinanciamentoTipo();
					gerFinanciamentoTipo.setId(financiamentoTipo);
				}				
				
				// Lancamento item Contabil
				GLancamentoItemContabil gerLancamentoItemContabil = null;
				if(lictId != null){
					gerLancamentoItemContabil = new GLancamentoItemContabil();
					gerLancamentoItemContabil.setId(lictId);
				}
				
				// Tipo de Documento
				GDocumentoTipo gerDocumentoTipo = null;
				if(tipoDocumento != null){
					gerDocumentoTipo = new GDocumentoTipo();
					gerDocumentoTipo.setId(tipoDocumento);
				}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				//	Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				//	Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}				
				// constuctor
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, 0, 
						0							, new BigDecimal(0)         , new BigDecimal(0),
						0							, gerDocumentoTipo			, gerFinanciamentoTipo,
						gerLancamentoItemContabil   , valorDocumentosFaturados  , quantidadeDocumentosFaturados.shortValue(), 
						ultimaAlteracao				, elo						, rota, 
						empresa						, indicadorHidrometro 		, new BigDecimal(0),
						gerConsumoTarifa, gerFaturamentoGrupo, helper.getCodigoRota());
				
				//	Adicionamos na tabela ResumoFaturamentoOutros
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);
				//System.out.println("gravando objeto de OUTROS");
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO OUTROS" + "\n" + " IMOVEL ====> " + idImovelError, ex);
			throw new EJBException(ex);
		}
	}
	
	/**  gerarResumoFaturamentoCreditos
	 * Marcio Roberto - 03/07/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoFaturamentoCreditos(int idSetor, int anoMes,
			int indice, int qtRegistros, List resumo) throws ControladorException, ErroRepositorioException {
		
		Integer idImovelError = 0;
		try {
			
			System.out.println("processando resumo faturamento creditos ");	
	
			List<ResumoFaturamentoCreditosHelper>  listaSimplificadaFaturamentoCreditos = new ArrayList();
			List<ResumoFaturamentoCreditosSetores> listaSimplificadaFaturamentoCreditosSetores = new ArrayList();
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			
			List resumoContasFaturamentoAguaEsgoto = resumo; 
				//this.repositorioGerencialFaturamento
			//.getContasResumoFaturamentoAguaEsgoto( idSetor, anoMes, indice, qtRegistros);				
			
			for (int c = 0; c < resumoContasFaturamentoAguaEsgoto.size(); c++) { 
				Object obj = resumoContasFaturamentoAguaEsgoto.get(c);
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					Integer idContaResumo = (Integer) retorno[0];
					Integer idImovelCreditos = (Integer) retorno[1];
					idImovelError = idImovelCreditos;
	
					Integer idEmpresaCreditos = (Integer) retorno[22];
	
					ResumoFaturamentoCreditosSetores listaSetores = new ResumoFaturamentoCreditosSetores(idSetor); 
											
						/*
						 * Alteração para corrigir o erro no resumo do faturamento na base gerencial
						 * onde os créditos eram persistidos na categoria errada
						 * autor: Wellington Rocha 
						 * data: 12/09/2011*/
						List resumoCreditoRealizado = this.repositorioGerencialFaturamento
						//.getPesquisaCreditoRealizado(idSetor,anoMes);					
						.pesquisarCreditosRealizadosResumoFaturamentoGerencial(idContaResumo); 
						for (int y = 0; y < resumoCreditoRealizado.size(); y++) {//1111
							Object objCreditos = resumoCreditoRealizado.get(y);
							if (obj instanceof Object[]) {
								Object[] retornoCreditos = (Object[]) objCreditos;
								ResumoFaturamentoCreditosHelper helperCreditos = new ResumoFaturamentoCreditosHelper(
										(Integer) retorno[1], // Imovel
										(Integer) retorno[2], // Gerencia Regional
										(Integer) retorno[3], // Unidade de negocio
										(Integer) retorno[4], // Elo
										(Integer) retorno[5], // Localidade
										(Integer) retorno[6], // Id Setor Comercial
										(Integer) retorno[7], // id Rota
										(Integer) retorno[8], // Id Quadra
										(Integer) retorno[9], // Codigo do Setor Comercial
										(Integer) retorno[10], // Numero da quadra
										(Integer) retorno[11], // Perfil do imovel
										(Integer) retorno[12], // Situacao da ligacao da agua
										(Integer) retorno[13], // Situacao da ligacao do esgoto
										(Integer) retorno[14], // Perfil da ligacao do agua
										(Integer) retorno[15],// Perfil da ligacao do esgoto
										(Short)   retorno[25]);// codigo rota
								//System.out.println("***************entrou em credito**********************");
								Integer anoMesReferencia = (Integer) retorno[20];
								
								//	Consumo Tarifa.
								Integer consumoTarifa = (Integer) retorno[23];
								if(consumoTarifa == null){
									consumoTarifa = 0;
								}
								helperCreditos.setConsumoTarifa(consumoTarifa);		
								
								//	Grupo de Faturamento
								Integer grupoFaturamento = (Integer) retorno[24];
								if(grupoFaturamento == null){
									grupoFaturamento = 0;
								}
								helperCreditos.setGrupoFaturamento(grupoFaturamento);
								
								// Pesquisamos a esfera de poder do cliente
								helperCreditos.setIdEsfera(this.repositorioGerencialFaturamento
												.pesquisarEsferaPoderClienteResponsavelImovel(helperCreditos
														.getIdImovel()));
								// Pesquisamos o tipo de cliente responsavel do
								helperCreditos.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
												.pesquisarTipoClienteClienteResponsavelImovel(helperCreditos
														.getIdImovel()));
								// Empresa
								helperCreditos.setGempresa(idEmpresaCreditos);
								// Categoria
								Categoria categoriaCreditos = null;
								categoriaCreditos = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovelCreditos);
								if (categoriaCreditos != null) {
									helperCreditos.setIdCategoria(categoriaCreditos.getId());
									// Pesquisando a principal subcategoria
									ImovelSubcategoria subcategoriaCreditos = this.getControladorImovel()
											.obterPrincipalSubcategoria(categoriaCreditos.getId(),idImovelCreditos);
									if (subcategoriaCreditos != null) {
										helperCreditos.setIdSubCategoria(subcategoriaCreditos.getComp_id().getSubcategoria().getId());
									}
								}
								// Verificamos se a esfera de poder foi encontrada
								if (helperCreditos.getIdEsfera().equals(0)) {
									Imovel imovel = new Imovel();
									imovel.setId(idImovelCreditos);
									Cliente clienteTempCreditos = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
									if (clienteTempCreditos != null) {
										helperCreditos.setIdEsfera(clienteTempCreditos.getClienteTipo().getEsferaPoder().getId());
									}
								}
								// Verificar existencia de cliente responsavel
								if (helperCreditos.getIdTipoClienteResponsavel().equals(0)) {
									Imovel imovel = new Imovel();
									imovel.setId(idImovelCreditos);
									Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
									if (clienteTemp != null) {
										helperCreditos.setIdTipoClienteResponsavel(clienteTemp.getClienteTipo().getId());
									}
								}
								
								// Campos referentes ao "resumoCreditoRealizado" ///////////////////////////////////////
								// Credito Origem
								Integer creditoOrigem = 0; 
								creditoOrigem =	(Integer) retornoCreditos[0];
								//if(creditoOrigem == null){
								//	creditoOrigem = 0;
								//}
								helperCreditos.setCreditoOrigem(creditoOrigem);
								
								// TipoCreditoOrigem
								Integer tipoCredito = 0;
								tipoCredito = (Integer) retornoCreditos[4];
								//if(tipoCredito == null){
								//	tipoCredito = 0;
								//}
								helperCreditos.setTipoCredito(tipoCredito);
								
								// Lancamento Item Contabil
								Integer lictId = 0;
								lictId = (Integer) retornoCreditos[1];
								//if(lictId == null){
								//	lictId = 0;
								//}
								helperCreditos.setLictId(lictId);
								
								//	[UC0307] - Obter Indicador de Existência de Hidrômetro
								String indicadorHidrometroString = new Integer(
										getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovelCreditos)).toString();
								Short indicadorHidrometro = new Short(indicadorHidrometroString);
								// Caso indicador de hidrômetro esteja nulo
								// Seta 2(dois) = NÃO no indicador de
								// hidrômetro
								if (indicadorHidrometro == null) {
									indicadorHidrometro = new Short("2");
								}
								helperCreditos.setIndicadorHidrometro(indicadorHidrometro);								

								// Valor dos Documentos Faturados
								BigDecimal valorDocumentosFaturados = new BigDecimal(0); 
								valorDocumentosFaturados = (BigDecimal) retornoCreditos[2];

								// Quantidade dos Documentos Faturados
								Integer quantidadeDocumentosFaturados = 0;
								quantidadeDocumentosFaturados = (Integer) retornoCreditos[3];
								
								// Tipo de Documento
								GCreditoOrigem gCreditoOrigem = null;
								//if(creditoOrigem != null){
									gCreditoOrigem = new GCreditoOrigem();
									gCreditoOrigem.setId(creditoOrigem);
								//}
								
								// Lancamento item Contabil
								GLancamentoItemContabil gerLancamentoItemContabil = null;
								//if(lictId != null){
									gerLancamentoItemContabil = new GLancamentoItemContabil();
									gerLancamentoItemContabil.setId(lictId);
								//}
								/////////////////////////////////////////////////////////////////////////////////////
								// informacos iguals 
								if (listaSimplificadaFaturamentoCreditos.contains(helperCreditos)) {
									int posicaoCreditos = listaSimplificadaFaturamentoCreditos.indexOf(helperCreditos);
									ResumoFaturamentoCreditosHelper jaCadastradoCreditos = 
										(ResumoFaturamentoCreditosHelper) listaSimplificadaFaturamentoCreditos.get(posicaoCreditos);
									// Somatorios
			
									// documentos faturados
									jaCadastradoCreditos.setQuantidadeDocumentosFaturados( 
											jaCadastradoCreditos.getQuantidadeDocumentosFaturados() + quantidadeDocumentosFaturados);
			
									// Valor Documentos faturados
									jaCadastradoCreditos.setValorDocumentosFaturados( 
											jaCadastradoCreditos.getValorDocumentosFaturados().add(valorDocumentosFaturados));
								} else {
									// Somatorios
									
									// documentos faturados
									helperCreditos.setQuantidadeDocumentosFaturados(quantidadeDocumentosFaturados); 
			
									// Valor Documentos faturados
									helperCreditos.setValorDocumentosFaturados(valorDocumentosFaturados);
									
									helperCreditos.setAnoMesReferencia(anoMesReferencia);
									
									listaSimplificadaFaturamentoCreditos.add(helperCreditos);
								}
							}// if instance of de Creditos
						}// for de Creditos
				}// if instance of de contas
			}// for de contas
			
			for (int i = 0; i < listaSimplificadaFaturamentoCreditos.size(); i++) {
				ResumoFaturamentoCreditosHelper helper = (ResumoFaturamentoCreditosHelper) listaSimplificadaFaturamentoCreditos.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	

				// Valor dos Documentos Faturados
				BigDecimal valorDocumentosFaturados = (BigDecimal) helper.getValorDocumentosFaturados();

				// Quantidade dos Documentos Faturados
				Integer quantidadeDocumentosFaturados = helper.getQuantidadeDocumentosFaturados();
				
				Integer creditoOrigem = helper.getCreditoOrigem();
				
				Integer lictId = helper.getLictId();
				
				// Tipo de Documento
				GCreditoOrigem gCreditoOrigem = null;
				//if(creditoOrigem != null){
					gCreditoOrigem = new GCreditoOrigem();
					gCreditoOrigem.setId(creditoOrigem);
				//}
				
				Integer creditoTipo = helper.getTipoCredito();
				
				// Tipo de Documento
				GCreditoTipo gCreditoTipo = null;
				//if(creditoTipo != null){
					gCreditoTipo = new GCreditoTipo();
					gCreditoTipo.setId(creditoTipo);
				//}
				
				// Lancamento item Contabil
				GLancamentoItemContabil gerLancamentoItemContabil = null;
				//if(lictId != null){
					gerLancamentoItemContabil = new GLancamentoItemContabil();
					gerLancamentoItemContabil.setId(lictId);
				//}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				//	Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				//	Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}

				GDocumentoTipo gerDocumentoTipo = new GDocumentoTipo();
				gerDocumentoTipo.setId(1);
				
				
				
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, 0, 
						0							, new BigDecimal(0)         , new BigDecimal(0),
						0							, gCreditoOrigem			, gerLancamentoItemContabil,
						valorDocumentosFaturados    , quantidadeDocumentosFaturados,  
						ultimaAlteracao				, elo						, rota, empresa,
						gCreditoTipo				, indicadorHidrometro 		, new BigDecimal(0),
						gerConsumoTarifa, gerFaturamentoGrupo,gerDocumentoTipo, helper.getCodigoRota());
				
				//	Adicionamos na tabela ResumoFaturamentoCreditos
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);
				//System.out.println("gravando objeto de CREDITOS");
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO CREDITOS" + "\n" + " IMOVEL ====> " + idImovelError, ex);
			throw new EJBException(ex);
		}
	}
	
	public Collection pesquisarIdsSetores() throws ControladorException {
		try {
			return repositorioGerencialFaturamento.pesquisarIdsSetores();
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}

	}
	
	
	
	/**  gerarResumoFaturamentoDebitoACobrar
	 * Marcio Roberto - 11/07/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoFaturamentoDebitosACobrar(int idSetor, int anoMes) throws ControladorException, ErroRepositorioException {
		Integer idImovelError = 0;
		try {
			
			System.out.println("processando resumo faturamento debitos a cobrar ");	
	
			List<ResumoFaturamentoDebitosACobrarHelper> listaSimplificadaFaturamentoDebitosACobrar = new ArrayList();
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			
			List resumoDebitoACobrar = this.repositorioGerencialFaturamento
					.getPesquisaDebitoACobrar(idSetor, anoMes);
			
			//Integer total = resumoDebitoACobrar.size();
			Integer reg = 0;

			for (int y = 0; y < resumoDebitoACobrar.size(); y++) {
				Object objDebitoACobrar = resumoDebitoACobrar.get(y);

				if (objDebitoACobrar instanceof Object[]) {
					Object[] retorno = (Object[]) objDebitoACobrar;

					Integer idImovelDebitosACobrar = (Integer) retorno[26];
					idImovelError = idImovelDebitosACobrar;
					//System.out.println("processando: "+reg+" de: "+total+" Debitos a cobrar do setor = "+idSetor+"  do  Imovel = "+idImovelDebitosACobrar);
					reg++;
					Integer idEmpresaDebitosACobrar = (Integer) retorno[18];

					ResumoFaturamentoDebitosACobrarHelper helperDebitosACobrar = new ResumoFaturamentoDebitosACobrarHelper(
							(Integer) retorno[26],// Imovel
							(Integer) retorno[3], // Gerencia Regional
							(Integer) retorno[4], // Unidade de negocio
							(Integer) retorno[5], // Elo
							(Integer) retorno[6], // Localidade
							(Integer) retorno[7], // Id Setor Comercial
							(Integer) retorno[8], // id Rota
							(Integer) retorno[9], // Id Quadra
							(Integer) retorno[10], // Codigo do Setor Comercial
							(Integer) retorno[11], // Numero da quadra
							(Integer) retorno[12], // Perfil do imovel
							(Integer) retorno[13], // Situacao da ligacao da agua
							(Integer) retorno[14], // Situacao da ligacao do esgoto
							(Integer) retorno[15], // Perfil da ligacao do agua
							(Integer) retorno[16],// Perfil da ligacao do esgoto
							(Short)   retorno[27]); // codigo rota

					Integer anoMesReferencia = anoMes;

					// Consumo Tarifa.
					Integer consumoTarifa = (Integer) retorno[19];
					if (consumoTarifa == null) {
						consumoTarifa = 0;
					}
					helperDebitosACobrar.setConsumoTarifa(consumoTarifa);

					// Grupo de Faturamento
					Integer grupoFaturamento = (Integer) retorno[20];
					if (grupoFaturamento == null) {
						grupoFaturamento = 0;
					}
					helperDebitosACobrar.setGrupoFaturamento(grupoFaturamento);

					// Pesquisamos a esfera de poder do cliente
					helperDebitosACobrar
							.setIdEsfera(this.repositorioGerencialFaturamento
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovelDebitosACobrar));

					// Pesquisamos o tipo de cliente responsavel 
					helperDebitosACobrar
							.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
									.pesquisarTipoClienteClienteResponsavelImovel(idImovelDebitosACobrar));
					// Empresa
					helperDebitosACobrar.setGempresa(idEmpresaDebitosACobrar);

					// Categoria
					Categoria categoriaDebitosACobrar = null;
					categoriaDebitosACobrar = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovelDebitosACobrar);
					if (categoriaDebitosACobrar != null) {
						helperDebitosACobrar.setIdCategoria(categoriaDebitosACobrar
								.getId());
						// Pesquisando a principal subcategoria
						ImovelSubcategoria subcategoriaDebitosACobrar = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(
										categoriaDebitosACobrar.getId(), idImovelDebitosACobrar);
						if (subcategoriaDebitosACobrar != null) {
							helperDebitosACobrar
									.setIdSubCategoria(subcategoriaDebitosACobrar
											.getComp_id().getSubcategoria()
											.getId());
						}
					}
					// Verificamos se a esfera de poder foi encontrada
					if (helperDebitosACobrar.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelDebitosACobrar);
						Cliente clienteTempDebitosACobrar = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTempDebitosACobrar != null) {
							helperDebitosACobrar.setIdEsfera(clienteTempDebitosACobrar
									.getClienteTipo().getEsferaPoder().getId());
						}
					}

					// Verificar existencia de cliente responsavel
					if (helperDebitosACobrar.getIdTipoClienteResponsavel()
							.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelDebitosACobrar);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helperDebitosACobrar
									.setIdTipoClienteResponsavel(clienteTemp
											.getClienteTipo().getId());
						}
					}

					// [UC0307] - Obter Indicador de Existência de Hidrômetro
					String indicadorHidrometroString = new Integer(
							getControladorImovel()
									.obterIndicadorExistenciaHidrometroImovel(
											idImovelDebitosACobrar)).toString();
					Short indicadorHidrometro = new Short(
							indicadorHidrometroString);
					// Caso indicador de hidrômetro esteja nulo
					// Seta 2(dois) = NÃO no indicador de
					// hidrômetro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helperDebitosACobrar
							.setIndicadorHidrometro(indicadorHidrometro);

					// Campos referentes ao "resumoDebitoCobrado"
					// ///////////////////////////////////////
					Integer financiamentoTipo = (Integer) retorno[0];
					if (financiamentoTipo == null) {
						financiamentoTipo = 0;
					}
					helperDebitosACobrar
							.setIdFinanciamentoTipo(financiamentoTipo);

					Integer lictId = (Integer) retorno[2];
					if (lictId == null) {
						lictId = 0;
					}
					helperDebitosACobrar.setLictId(lictId);

					Integer documentoTipo = (Integer) retorno[1];
					if (documentoTipo == null) {
						documentoTipo = 0;
					}
					helperDebitosACobrar.setIdDocumentoTipo(documentoTipo);

					Integer debitoTipo = (Integer) retorno[25];
					if (debitoTipo == null) {
						debitoTipo = 0;
					}
					helperDebitosACobrar.setDebitoTipo(debitoTipo);

					// Valor dos Financiamentos Incluidos Debitos a Cobrar
					BigDecimal valorFinanciamentosIncluidos = (BigDecimal) retorno[21];
					if (valorFinanciamentosIncluidos == null) {
						valorFinanciamentosIncluidos = new BigDecimal(0);
					}

					// Valor dos Financiamentos Cancelados Debitos a Cobrar
					BigDecimal valorFinanciamentosCancelados = (BigDecimal) retorno[23];
					if (valorFinanciamentosCancelados == null) {
						valorFinanciamentosCancelados = new BigDecimal(0);
					}
					
					// Valor Juros Parcelamento
					BigDecimal valorJurosParcelamento = (BigDecimal) retorno[29];
					if (valorJurosParcelamento == null) {
						valorJurosParcelamento = new BigDecimal(0);
					}

					// Valor Parcelamentos Cancelados
					BigDecimal valorParcelamentosCancelados = (BigDecimal) retorno[30];
					if (valorParcelamentosCancelados == null) {
						valorParcelamentosCancelados = new BigDecimal(0);
					}

					// ///////////////////////////////////////////////////////////////////////////////////
					// informacos iguals
					if (listaSimplificadaFaturamentoDebitosACobrar.contains(helperDebitosACobrar)) {
						int posicaoDebitosACobrar = listaSimplificadaFaturamentoDebitosACobrar.indexOf(helperDebitosACobrar);
						ResumoFaturamentoDebitosACobrarHelper jaCadastradoOutros = (ResumoFaturamentoDebitosACobrarHelper) listaSimplificadaFaturamentoDebitosACobrar
								.get(posicaoDebitosACobrar);

						// Valor dos Financiamentos Incluidos Debitos a Cobrar
						jaCadastradoOutros
								.setValorFinanciamentosIncluidos(jaCadastradoOutros
										.getValorFinanciamentosIncluidos().add(
												valorFinanciamentosIncluidos));

						// Valor dos Financiamentos Cancelados Debitos a Cobrar
						jaCadastradoOutros
								.setValorFinanciamentosCancelados(jaCadastradoOutros
										.getValorFinanciamentosCancelados()
										.add(valorFinanciamentosCancelados));
						
						// Valor Juros Parcelamento
						jaCadastradoOutros
								.setValorJurosParcelamento(jaCadastradoOutros
										.getValorJurosParcelamento()
										.add(valorJurosParcelamento));
						
						// Valor ParcelamentosCancelados
						jaCadastradoOutros
								.setValorParcelamentosCancelados(jaCadastradoOutros
										.getValorParcelamentosCancelados()
										.add(valorParcelamentosCancelados));

					} else {
						// Somatorios

						// Valor dos Financiamentos Incluidos Debitos a Cobrar
						helperDebitosACobrar
								.setValorFinanciamentosIncluidos(valorFinanciamentosIncluidos);

						// Valor dos Financiamentos Cancelados Debitos a Cobrar
						helperDebitosACobrar
								.setValorFinanciamentosCancelados(valorFinanciamentosCancelados);
						
						// Valor Juros Parcelamento
						helperDebitosACobrar
								.setValorJurosParcelamento(valorJurosParcelamento);
						
						// Valor Parcelamento Cancelados
						helperDebitosACobrar
								.setValorParcelamentosCancelados(valorParcelamentosCancelados);

						helperDebitosACobrar
								.setAnoMesReferencia(anoMesReferencia);

						listaSimplificadaFaturamentoDebitosACobrar
								.add(helperDebitosACobrar);
					}
				}// if instance of de debito a cobrar
			}// for de debito a cobrar
			
			for (int i = 0; i < listaSimplificadaFaturamentoDebitosACobrar.size(); i++) {
				ResumoFaturamentoDebitosACobrarHelper helper = (ResumoFaturamentoDebitosACobrarHelper) listaSimplificadaFaturamentoDebitosACobrar.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	
				
				GDebitoTipo gerDebitoTipo = null;
				if(helper.getDebitoTipo() != null ){
					gerDebitoTipo = new GDebitoTipo();
					gerDebitoTipo.setId( helper.getDebitoTipo() );
				}	

				// Valor dos Documentos incluidos
				BigDecimal valorFinanciamentosIncluidos = (BigDecimal) helper.getValorFinanciamentosIncluidos();

				// Valor dos Documentos incluidos
				BigDecimal valorFinanciamentosCancelados = (BigDecimal) helper.getValorFinanciamentosCancelados();
				
				// Valor Juros Parcelamentos
				BigDecimal valorJurosParcelamentos = (BigDecimal) helper.getValorJurosParcelamento();
				
				// Valor Parcelamentos Cancelados
				BigDecimal valorParcelamentosCancelados = (BigDecimal) helper.getValorParcelamentosCancelados();

				Integer financiamentoTipo = helper.getIdFinanciamentoTipo();
				Integer lictId = helper.getLictId();
				Integer tipoDocumento = helper.getIdDocumentoTipo();
				
				// Financiamento Tipo
				GFinanciamentoTipo gerFinanciamentoTipo = null;
				if(financiamentoTipo != null ){
					gerFinanciamentoTipo = new GFinanciamentoTipo();
					gerFinanciamentoTipo.setId(financiamentoTipo);
				}				
				
				// Lancamento item Contabil
				GLancamentoItemContabil gerLancamentoItemContabil = null;
				if(lictId != null){
					gerLancamentoItemContabil = new GLancamentoItemContabil();
					gerLancamentoItemContabil.setId(lictId);
				}
				
				// Tipo de Documento
				GDocumentoTipo gerDocumentoTipo = null;
				if(tipoDocumento != null){
					gerDocumentoTipo = new GDocumentoTipo();
					gerDocumentoTipo.setId(tipoDocumento);
				}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				//	Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				//	Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}				
				
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, 0, 
						0							, new BigDecimal(0)         , new BigDecimal(0),
						0							, gerDocumentoTipo			, gerFinanciamentoTipo,
						gerLancamentoItemContabil   , new BigDecimal(0)			, null, 
						ultimaAlteracao				, elo						, rota, 
						empresa						, valorFinanciamentosIncluidos, valorFinanciamentosCancelados,
						gerDebitoTipo				, indicadorHidrometro 		, new BigDecimal(0),
						gerConsumoTarifa, gerFaturamentoGrupo, helper.getCodigoRota());
				
				resumoFaturamentoAguaEsgotoGrava.setValorJurosParcelamento(valorJurosParcelamentos);
				resumoFaturamentoAguaEsgotoGrava.setValorParcelamentosCancelado(valorParcelamentosCancelados);
				
				//	Adicionamos na tabela ResumoFaturamentoOutros
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);
				//System.out.println("gravando objeto de DEBITO A COBRAR");
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO DEBITO A COBRAR" + "\n" + " IMOVEL ====> " + idImovelError, ex);
			throw new EJBException(ex);
		}
	}

	/**  gerarResumoFaturamentoImpostos
	 * Roberto Barbalho - 28/08/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoFaturamentoImpostos(int idSetor, int anoMes,
			int indice, int qtRegistros, List resumo) throws ControladorException, ErroRepositorioException {
		
		Integer idImovelError = 0;
		try {
			System.out.println("processando resumo faturamento impostos ");	
			List<ResumoFaturamentoImpostosHelper> listaSimplificadaFaturamentoImpostos = new ArrayList();
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			
			List resumoContasFaturamentoAguaEsgoto = resumo; 
				//this.repositorioGerencialFaturamento
			//.getContasResumoFaturamentoAguaEsgoto( idSetor, anoMes, indice, qtRegistros);				
			
			for (int c = 0; c < resumoContasFaturamentoAguaEsgoto.size(); c++) {
				Object obj = resumoContasFaturamentoAguaEsgoto.get(c);
				Integer idContaResumo = 0;
				if (obj instanceof Object[]) {
					Object[] retorno = (Object[]) obj;
					idContaResumo = (Integer) retorno[0];
					
					Integer idImovelImpostos = (Integer) retorno[1];
					idImovelError = idImovelImpostos;
	
					Integer idEmpresaImpostos = (Integer) retorno[22];
	
					// ResumoFaturamentoOutros
					List resumoImpostos = this.repositorioGerencialFaturamento
							.getPesquisaImpostos(idContaResumo);
	
					for (int y = 0; y < resumoImpostos.size(); y++) {
						Object objImpostos = resumoImpostos.get(y);
						if (obj instanceof Object[]) {
							Object[] retornoImpostos = (Object[]) objImpostos;
							ResumoFaturamentoImpostosHelper helperImpostos = new ResumoFaturamentoImpostosHelper(
									(Integer) retorno[1], // Imovel
									(Integer) retorno[2], // Gerencia Regional
									(Integer) retorno[3], // Unidade de negocio
									(Integer) retorno[4], // Elo
									(Integer) retorno[5], // Localidade
									(Integer) retorno[6], // Id Setor Comercial
									(Integer) retorno[7], // id Rota
									(Integer) retorno[8], // Id Quadra
									(Integer) retorno[9], // Codigo do Setor Comercial
									(Integer) retorno[10], // Numero da quadra
									(Integer) retorno[11], // Perfil do imovel
									(Integer) retorno[12], // Situacao da ligacao da agua
									(Integer) retorno[13], // Situacao da ligacao do esgoto
									(Integer) retorno[14], // Perfil da ligacao do agua
									(Integer) retorno[15],// Perfil da ligacao do esgoto
									(Short)   retorno[25]);// codigo rota
							
							Integer anoMesReferencia = (Integer) retorno[20];
							
							//	Consumo Tarifa.
							Integer consumoTarifa = (Integer) retorno[23];
							if(consumoTarifa == null){
								consumoTarifa = 0;
							}
							helperImpostos.setConsumoTarifa(consumoTarifa);
							
							//	Grupo de Faturamento
							Integer grupoFaturamento = (Integer) retorno[24];
							if(grupoFaturamento == null){
								grupoFaturamento = 0;
							}
							helperImpostos.setGrupoFaturamento(grupoFaturamento);
	
							// Pesquisamos a esfera de poder do cliente
							helperImpostos.setIdEsfera(this.repositorioGerencialFaturamento
											.pesquisarEsferaPoderClienteResponsavelImovel(helperImpostos
													.getIdImovel()));
							// Pesquisamos o tipo de cliente responsavel do
							helperImpostos.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
											.pesquisarTipoClienteClienteResponsavelImovel(helperImpostos
													.getIdImovel()));
							// Empresa
							helperImpostos.setGempresa(idEmpresaImpostos);
							// Categoria
							Categoria categoriaImpostos = null;
							categoriaImpostos = this.getControladorImovel().obterPrincipalCategoriaImovel(idImovelImpostos);
							if (categoriaImpostos != null) {
								helperImpostos.setIdCategoria(categoriaImpostos.getId());
								// Pesquisando a principal subcategoria
								ImovelSubcategoria subcategoriaImpostos = this.getControladorImovel()
										.obterPrincipalSubcategoria(categoriaImpostos.getId(),idImovelImpostos);
								if (subcategoriaImpostos != null) {
									helperImpostos.setIdSubCategoria(subcategoriaImpostos.getComp_id().getSubcategoria().getId());
								}
							}
							// Verificamos se a esfera de poder foi encontrada
							if (helperImpostos.getIdEsfera().equals(0)) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelImpostos);
								Cliente clienteTempImpostos = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
								if (clienteTempImpostos != null) {
									helperImpostos.setIdEsfera(clienteTempImpostos.getClienteTipo().getEsferaPoder().getId());
								}
							}
							// Verificar existencia de cliente responsavel
							if (helperImpostos.getIdTipoClienteResponsavel().equals(0)) {
								Imovel imovel = new Imovel();
								imovel.setId(idImovelImpostos);
								Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
								if (clienteTemp != null) {
									helperImpostos.setIdTipoClienteResponsavel(clienteTemp.getClienteTipo().getId());
								}
							}
							
							//	[UC0307] - Obter Indicador de Existência de Hidrômetro
							String indicadorHidrometroString = new Integer(
									getControladorImovel().obterIndicadorExistenciaHidrometroImovel(idImovelImpostos)).toString();
							Short indicadorHidrometro = new Short(indicadorHidrometroString);
							// Caso indicador de hidrômetro esteja nulo
							// Seta 2(dois) = NÃO no indicador de
							// hidrômetro
							if (indicadorHidrometro == null) {
								indicadorHidrometro = new Short("2");
							}
							helperImpostos.setIndicadorHidrometro(indicadorHidrometro);	
							
							
							Integer impostoTipo = (Integer) retornoImpostos[0];
							if(impostoTipo == null){
								impostoTipo = 0;
							}
							helperImpostos.setImpostoTipo(impostoTipo);
							
							BigDecimal valorImpostos = (BigDecimal) retornoImpostos[1];
							if(valorImpostos == null){
								valorImpostos = new BigDecimal(0);
							}
	
							// Valor dos Documentos Faturados
							BigDecimal valorDocumentosFaturados = new BigDecimal(0);
							if (valorDocumentosFaturados == null) {
								valorDocumentosFaturados = new BigDecimal(0);
							}
							// Quantidade dos Documentos Faturados
							Integer quantidadeDocumentosFaturados = 0;
							if (quantidadeDocumentosFaturados == null) {
								quantidadeDocumentosFaturados = 0;
							}
							/////////////////////////////////////////////////////////////////////////////////////
							// informacos iguals 
							
							if (listaSimplificadaFaturamentoImpostos.contains(helperImpostos)) {
								int posicaoImpostos = listaSimplificadaFaturamentoImpostos.indexOf(helperImpostos);
								ResumoFaturamentoImpostosHelper jaCadastradoImpostos = 
									(ResumoFaturamentoImpostosHelper) listaSimplificadaFaturamentoImpostos.get(posicaoImpostos);
								// Somatorios
		
								// Valor Impostos
								jaCadastradoImpostos.setValorImpostos(valorImpostos);
		
							} else {
								// Somatorios
								
								//	Valor Impostos
								helperImpostos.setValorImpostos(valorImpostos);
								
								helperImpostos.setAnoMesReferencia(anoMesReferencia);
								
								listaSimplificadaFaturamentoImpostos.add(helperImpostos);
							}
						}// if instance of de outros
					}// for de outros
				}// if instance of de contas
			}// for de contas
			
			for (int i = 0; i < listaSimplificadaFaturamentoImpostos.size(); i++) {
				ResumoFaturamentoImpostosHelper helper = (ResumoFaturamentoImpostosHelper) listaSimplificadaFaturamentoImpostos.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	

				// Imposto Tipo
				GImpostoTipo impostoTipo = null;
				if(helper.getImpostoTipo() != null ){
					impostoTipo = new GImpostoTipo();
					impostoTipo.setId(helper.getImpostoTipo());
				}	
				
				
				// Valor Impostos
				BigDecimal valorImpostos = helper.getValorImpostos();
				
				// Valor dos Documentos Faturados
				BigDecimal valorDocumentosFaturados = (BigDecimal) helper.getValorDocumentosFaturados();

				// Quantidade dos Documentos Faturados
				Integer quantidadeDocumentosFaturados = helper.getQuantidadeDocumentosFaturados();
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				//	Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				//	Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}				

				GDocumentoTipo gerDocumentoTipo = new GDocumentoTipo();
				gerDocumentoTipo.setId(1);
				
				
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional  		, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria,
						perfilLigacaoAgua			, perfilLigacaoEsgoto       , 0,
						0							, new BigDecimal(0)         , new BigDecimal(0),
						0							, valorDocumentosFaturados  , quantidadeDocumentosFaturados.shortValue(),
						ultimaAlteracao				, elo						, rota, 
						empresa						, impostoTipo				, valorImpostos, 
						indicadorHidrometro, gerConsumoTarifa, gerFaturamentoGrupo,gerDocumentoTipo, helper.getCodigoRota());
				
				//	Adicionamos na tabela ResumoFaturamentoOutros
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);
				//System.out.println("gravando objeto de IMPOSTOS");
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO IMPOSTOS" + "\n" + " IMOVEL ====> " + idImovelError, ex);
			throw new EJBException(ex);
		}
	}
	
	
	/**  gerarResumoFaturamentoGuiaPagamento
	 * Marcio Roberto - 06/09/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoFaturamentoGuiaPagamento(int idSetor, int anoMes) throws ControladorException, ErroRepositorioException {
		Integer idImovelError = 0;
		
		try {

			System.out.println("processando resumo faturamento Guias de Pagamento ");	
	
			List<ResumoFaturamentoGuiaPagamentoHelper> listaSimplificadaFaturamentoGuiaPagamento = new ArrayList();
			UnResumoFaturamento resumoFaturamentoAguaEsgotoGrava = null;
			
			/* 
			 * Alteraçãopara pesquisa que não considera as guias de pagamento de entrada de parcelamento
			 * Autor: Wellington Rocha
			 * Data: 14/09/2011*/
			List resumoGuiaPagamento = this.repositorioGerencialFaturamento
			//		.getPesquisaGuiaPagamento(idSetor, anoMes);
			.pesquisarGuiaPagamentoResumoFaturamentoGerencial(idSetor, anoMes);
			
			//Integer total = resumoGuiaPagamento.size();
			Integer reg = 0;

			for (int y = 0; y < resumoGuiaPagamento.size(); y++) {
				Object objGuiaPagamento = resumoGuiaPagamento.get(y);

				if (objGuiaPagamento instanceof Object[]) {
					Object[] retorno = (Object[]) objGuiaPagamento;

					Integer idImovelGuiaPagamento = (Integer) retorno[24];
					idImovelError = idImovelGuiaPagamento;
					//System.out.println("processando: "+reg+" de: "+total+" Guias de Pagamento do setor = "+idSetor+"  do  Imovel = "+idImovelGuiaPagamento);
					reg++;
					Integer idEmpresaGuiaPagamento = (Integer) retorno[18];

					ResumoFaturamentoGuiaPagamentoHelper helperGuiaPagamento = new ResumoFaturamentoGuiaPagamentoHelper(
							(Integer) retorno[24],// Imovel
							(Integer) retorno[3], // Gerencia Regional
							(Integer) retorno[4], // Unidade de negocio
							(Integer) retorno[5], // Elo
							(Integer) retorno[6], // Localidade
							(Integer) retorno[7], // Id Setor Comercial
							(Integer) retorno[8], // id Rota
							(Integer) retorno[9], // Id Quadra
							(Integer) retorno[10], // Codigo do Setor Comercial
							(Integer) retorno[11], // Numero da quadra
							(Integer) retorno[12], // Perfil do imovel
							(Integer) retorno[13], // Situacao da ligacao da agua
							(Integer) retorno[14], // Situacao da ligacao do esgoto
							(Integer) retorno[15], // Perfil da ligacao do agua
							(Integer) retorno[16],// Perfil da ligacao do esgoto
							(Short)   retorno[25]);// codigo rota

					Integer anoMesReferencia = anoMes;

					// Consumo Tarifa.
					Integer consumoTarifa = (Integer) retorno[19];
					if (consumoTarifa == null) {
						consumoTarifa = 0;
					}
					helperGuiaPagamento.setConsumoTarifa(consumoTarifa);

					// Grupo de Faturamento
					Integer grupoFaturamento = (Integer) retorno[20];
					if (grupoFaturamento == null) {
						grupoFaturamento = 0;
					}
					helperGuiaPagamento.setGrupoFaturamento(grupoFaturamento);

					// Pesquisamos a esfera de poder do cliente
					helperGuiaPagamento
							.setIdEsfera(this.repositorioGerencialFaturamento
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovelGuiaPagamento));

					// Pesquisamos o tipo de cliente responsavel 
					helperGuiaPagamento
							.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
									.pesquisarTipoClienteClienteResponsavelImovel(idImovelGuiaPagamento));
					// Empresa
					helperGuiaPagamento.setGempresa(idEmpresaGuiaPagamento);

					// Categoria
					Categoria categoriaGuiaPagamento = null;
					categoriaGuiaPagamento = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovelGuiaPagamento);
					if (categoriaGuiaPagamento != null) {
						helperGuiaPagamento.setIdCategoria(categoriaGuiaPagamento
								.getId());
						// Pesquisando a principal subcategoria
						ImovelSubcategoria subCategoriaGuiaPagamento = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(
										categoriaGuiaPagamento.getId(), idImovelGuiaPagamento);
						if (subCategoriaGuiaPagamento != null) {
							helperGuiaPagamento
									.setIdSubCategoria(subCategoriaGuiaPagamento
											.getComp_id().getSubcategoria()
											.getId());
						}
					}
					// Verificamos se a esfera de poder foi encontrada
					if (helperGuiaPagamento.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTempGuiaPagamento = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTempGuiaPagamento != null) {
							helperGuiaPagamento.setIdEsfera(clienteTempGuiaPagamento
									.getClienteTipo().getEsferaPoder().getId());
						}
					}

					// Verificar existencia de cliente responsavel
					if (helperGuiaPagamento.getIdTipoClienteResponsavel()
							.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helperGuiaPagamento
									.setIdTipoClienteResponsavel(clienteTemp
											.getClienteTipo().getId());
						}
					}

					// [UC0307] - Obter Indicador de Existência de Hidrômetro
					String indicadorHidrometroString = new Integer(
							getControladorImovel()
									.obterIndicadorExistenciaHidrometroImovel(
											idImovelGuiaPagamento)).toString();
					Short indicadorHidrometro = new Short(
							indicadorHidrometroString);
					// Caso indicador de hidrômetro esteja nulo
					// Seta 2(dois) = NÃO no indicador de
					// hidrômetro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helperGuiaPagamento
							.setIndicadorHidrometro(indicadorHidrometro);

					// Campos referentes ao "resumoDebitoCobrado"
					// ///////////////////////////////////////
					Integer financiamentoTipo = (Integer) retorno[0];
					if (financiamentoTipo == null) {
						financiamentoTipo = 0;
					}
					helperGuiaPagamento
							.setIdFinanciamentoTipo(financiamentoTipo);

					Integer lictId = (Integer) retorno[2];
					if (lictId == null) {
						lictId = 0;
					}
					helperGuiaPagamento.setLictId(lictId);

					Integer documentoTipo = (Integer) retorno[1];
					if (documentoTipo == null) {
						documentoTipo = 0;
					}
					helperGuiaPagamento.setIdDocumentoTipo(documentoTipo);

					Integer debitoTipo = (Integer) retorno[23];
					if (debitoTipo == null) {
						debitoTipo = 0;
					}
					helperGuiaPagamento.setDebitoTipo(debitoTipo);

					// Valor dos Documentos Faturados  bobobo
					BigDecimal valorDocumentosFaturados = (BigDecimal) retorno[21];
					if (valorDocumentosFaturados == null) {
						valorDocumentosFaturados = new BigDecimal(0);
					}
					//System.out.println((retorno[22]).getClass().getName());
					// Quantidade dos Documentos Faturados
					Integer quantidadeDocumentosFaturados = (Integer) retorno[22];
					if (quantidadeDocumentosFaturados == null) {
						quantidadeDocumentosFaturados = 0;
					}

					// ///////////////////////////////////////////////////////////////////////////////////
					// informacos iguals
					if (listaSimplificadaFaturamentoGuiaPagamento.contains(helperGuiaPagamento)) {
						int posicaoGuiaPagamento = listaSimplificadaFaturamentoGuiaPagamento.indexOf(helperGuiaPagamento);
						ResumoFaturamentoGuiaPagamentoHelper jaCadastradoGuiaPagamento = (ResumoFaturamentoGuiaPagamentoHelper) listaSimplificadaFaturamentoGuiaPagamento
								.get(posicaoGuiaPagamento);

						// documentos faturados
						jaCadastradoGuiaPagamento.setQuantidadeDocumentosFaturados( 
								jaCadastradoGuiaPagamento.getQuantidadeDocumentosFaturados() + quantidadeDocumentosFaturados);

						// Valor Documentos faturados
						jaCadastradoGuiaPagamento.setValorDocumentosFaturados( 
								jaCadastradoGuiaPagamento.getValorDocumentosFaturados().add(valorDocumentosFaturados));

					} else {
						// Somatorios

						// Valor dos Financiamentos Incluidos Debitos a Cobrar
						helperGuiaPagamento
								.setQuantidadeDocumentosFaturados(quantidadeDocumentosFaturados);

						// Valor dos Financiamentos Cancelados Debitos a Cobrar
						helperGuiaPagamento
								.setValorDocumentosFaturados(valorDocumentosFaturados);

						helperGuiaPagamento
								.setAnoMesReferencia(anoMesReferencia);

						listaSimplificadaFaturamentoGuiaPagamento
								.add(helperGuiaPagamento);
					}
				}// if instance of de debito a cobrar
			}// for de debito a cobrar
			
			for (int i = 0; i < listaSimplificadaFaturamentoGuiaPagamento.size(); i++) {
				ResumoFaturamentoGuiaPagamentoHelper helper = (ResumoFaturamentoGuiaPagamentoHelper) listaSimplificadaFaturamentoGuiaPagamento.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				Integer codigoSetorComercial = null;
				if (helper.getCodigoSetorComercial() != null) {
					codigoSetorComercial = (helper.getCodigoSetorComercial());
				}
				
				// Numero da quadra
				Integer numeroQuadra = null;
				if (helper.getNumeroQuadra() != null) {
					numeroQuadra = (helper.getNumeroQuadra());
				}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	
				
				GDebitoTipo gerDebitoTipo = null;
				if(helper.getDebitoTipo() != null ){
					gerDebitoTipo = new GDebitoTipo();
					gerDebitoTipo.setId( helper.getDebitoTipo() );
				}	

				// Valor dos Documentos Faturados 
				BigDecimal valorDocumentosFaturados = (BigDecimal) helper.getValorDocumentosFaturados();

				// Quantidade dos Documentos Faturados
				Integer quantidadeDocumentosFaturados = helper.getQuantidadeDocumentosFaturados();

				Integer financiamentoTipo = helper.getIdFinanciamentoTipo();
				Integer lictId = helper.getLictId();
				Integer tipoDocumento = helper.getIdDocumentoTipo();
				
				// Financiamento Tipo
				GFinanciamentoTipo gerFinanciamentoTipo = null;
				if(financiamentoTipo != null ){
					gerFinanciamentoTipo = new GFinanciamentoTipo();
					gerFinanciamentoTipo.setId(financiamentoTipo);
				}				
				
				// Lancamento item Contabil
				GLancamentoItemContabil gerLancamentoItemContabil = null;
				if(lictId != null){
					gerLancamentoItemContabil = new GLancamentoItemContabil();
					gerLancamentoItemContabil.setId(lictId);
				}
				
				// Tipo de Documento
				GDocumentoTipo gerDocumentoTipo = null;
				if(tipoDocumento != null){
					gerDocumentoTipo = new GDocumentoTipo();
					gerDocumentoTipo.setId(tipoDocumento);
				}
				
				Short indicadorHidrometro = helper.getIndicadorHidrometro();
				
				//	Consumo Tarifa
				GConsumoTarifa gerConsumoTarifa = null;
				if(helper.getConsumoTarifa() != null ){
					gerConsumoTarifa = new GConsumoTarifa();
					gerConsumoTarifa.setId( helper.getConsumoTarifa() );
				}
				
				//	Grupo Faturamento
				GFaturamentoGrupo gerFaturamentoGrupo = null;
				if(helper.getGrupoFaturamento() != null ){
					gerFaturamentoGrupo = new GFaturamentoGrupo();
					gerFaturamentoGrupo.setId( helper.getGrupoFaturamento() );
				}				
				resumoFaturamentoAguaEsgotoGrava = new UnResumoFaturamento(
						anoMesReferencia			, gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						codigoSetorComercial		, numeroQuadra				, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, 0, 
						0							, new BigDecimal(0)         , new BigDecimal(0),
						0							, gerDocumentoTipo			, gerFinanciamentoTipo,
						gerLancamentoItemContabil   , valorDocumentosFaturados	, quantidadeDocumentosFaturados.shortValue(), 
						ultimaAlteracao				, elo						, rota, 
						empresa						, new BigDecimal(0)			, new BigDecimal(0),
						gerDebitoTipo				, indicadorHidrometro 		, new BigDecimal(0),
						gerConsumoTarifa, gerFaturamentoGrupo, helper.getCodigoRota());
				
				//	Adicionamos na tabela ResumoFaturamentoOutros
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoFaturamentoAguaEsgotoGrava);
				//System.out.println("gravando objeto de GUIA DE PAGAMENTO");
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO GUIA DE PAGAMENTO" + "\n" + " IMOVEL ====> " + idImovelError, ex);
			throw new EJBException(ex);
		}
	}

	
	/**
	 * Retorna o menor dos maiores mês/ano das tabelas utilizadas para atualizar o resumo
	 * 
	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 */
	private Integer obterMenorEntreMaioresAnoMesTabelasParaResumoIndicadoresFaturamento()
			throws ControladorException {
	
		Integer retorno = null;

		try {

			Collection<Integer> colecaoAnosMeses = new ArrayList<Integer>();
			
			Integer anoMesResumoFaturamento = this.repositorioGerencialFaturamento
					.pesquisarMaiorAnoMesResumoFaturamento();
			colecaoAnosMeses.add(anoMesResumoFaturamento);

			Integer anoMesResumoRefaturamento = this.repositorioGerencialFaturamento
					.pesquisarMaiorAnoMesResumoRefaturamento();
			colecaoAnosMeses.add(anoMesResumoRefaturamento);
			
			if (colecaoAnosMeses != null && !colecaoAnosMeses.isEmpty()) {
				for (Integer anoMes : colecaoAnosMeses) {
					if (anoMes != null && (retorno == null || retorno.intValue() > anoMes.intValue())) {
						retorno = anoMes;
					}
				}
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
		}

		return retorno;
	}
	
	/**
	 * Método que gera resumo indicadores do faturamento
	 * 
	 * [UC????] - Gerar Resumo Indicadores do Faturamento
	 * 
	 * @author Rafael Corrêa
	 * @date 25/03/2008
	 * 
	 */
	public void gerarResumoIndicadoresFaturamento(int idFuncionalidadeIniciada)
			throws ControladorException {
		
		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o inï¿?cio do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.FUNCIONALIDADE, 0);

		
		
		try {
			Integer anoMesTabelas = this.obterMenorEntreMaioresAnoMesTabelasParaResumoIndicadoresFaturamento();
			Integer anoMesResumoIndicadorFaturamento = this.repositorioGerencialFaturamento
					.pesquisarMaiorAnoMesResumoIndicadoresFaturamento();
			
//			if (anoMesTabelas == null
//					|| (anoMesResumoIndicadorMicromedicao != null && anoMesTabelas
//							.intValue() <= anoMesResumoIndicadorMicromedicao
//							.intValue())) {
//				throw new ControladorException(
//						"atencao.ano.mes.indicador.maior.igual.ano.mes.resumo", null, "da Micromedição");
//			}
			
			if (anoMesTabelas != null
					&& (anoMesResumoIndicadorFaturamento == null || anoMesTabelas
							.intValue() > anoMesResumoIndicadorFaturamento
							.intValue())) {
				this.repositorioGerencialFaturamento.atualizarDadosResumoIndicadoresFaturamento(anoMesResumoIndicadorFaturamento, anoMesTabelas);
			}
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
					idUnidadeIniciada, false);
			
		} catch (Exception ex) {
			// Este catch serve para interceptar qualquer exceï¿?ï¿?o que o processo
			// batch venha a lanï¿?ar e garantir que a unidade de processamento do
			// batch serï¿? atualizada com o erro ocorrido
			ex.printStackTrace();
			// sessionContext.setRollbackOnly();

			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,
					idUnidadeIniciada, true);

			throw new EJBException(ex);
		}
		
	}
	
	/**  gerarResumoReFaturamentoGuiaPagamento
	 * Marcio Roberto - 16/11/2007
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoReFaturamentoGuiaPagamento(int idSetor, int anoMes) throws ControladorException, ErroRepositorioException {
		try {

			System.out.println("processando resumo refaturamento Guias de Pagamento para o setor:"+idSetor);	
	
			List<ResumoFaturamentoGuiaPagamentoHelper> listaSimplificadaReFaturamentoGuiaPagamento = new ArrayList();
			//UnResumoRefaturamento resumoReFaturamentoAguaEsgotoGrava = null;
			
			List resumoGuiaPagamento = this.repositorioGerencialFaturamento
					.getPesquisaGuiaPagamentoRefaturamento(idSetor, anoMes);
			
			Integer total = resumoGuiaPagamento.size();
			Integer reg = 0;

			for (int y = 0; y < resumoGuiaPagamento.size(); y++) {
				Object objGuiaPagamento = resumoGuiaPagamento.get(y);

				if (objGuiaPagamento instanceof Object[]) {
					Object[] retorno = (Object[]) objGuiaPagamento;

					Integer idImovelGuiaPagamento = (Integer) retorno[18];
					System.out.println("processando: "+reg+" de: "+total+" Guias de Pagamento do setor = "+idSetor+"  do  Imovel = "+idImovelGuiaPagamento);
					reg++;

					ResumoFaturamentoGuiaPagamentoHelper helperGuiaPagamento = new ResumoFaturamentoGuiaPagamentoHelper(
							(Integer) retorno[18],// Imovel
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Elo
							(Integer) retorno[4], // Localidade
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // id Rota
							(Integer) retorno[7], // Id Quadra
							(Integer) retorno[8], // Codigo do Setor Comercial
							(Integer) retorno[9], // Numero da quadra
							(Integer) retorno[10], // Perfil do imovel
							(Integer) retorno[11], // Situacao da ligacao da agua
							(Integer) retorno[12], // Situacao da ligacao do esgoto
							(Integer) retorno[13], // Perfil da ligacao do agua
							(Integer) retorno[14],// Perfil da ligacao do esgoto
							(Short)   retorno[25]); // codigo rota

					Integer anoMesReferencia = anoMes;

					// Pesquisamos a esfera de poder do cliente
					helperGuiaPagamento
							.setIdEsfera(this.repositorioGerencialFaturamento
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovelGuiaPagamento));

					// Pesquisamos o tipo de cliente responsavel 
					helperGuiaPagamento
							.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
									.pesquisarTipoClienteClienteResponsavelImovel(idImovelGuiaPagamento));

					// Categoria
					Categoria categoriaGuiaPagamento = null;
					categoriaGuiaPagamento = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovelGuiaPagamento);
					if (categoriaGuiaPagamento != null) {
						helperGuiaPagamento.setIdCategoria(categoriaGuiaPagamento
								.getId());
						// Pesquisando a principal subcategoria
						ImovelSubcategoria subCategoriaGuiaPagamento = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(
										categoriaGuiaPagamento.getId(), idImovelGuiaPagamento);
						if (subCategoriaGuiaPagamento != null) {
							helperGuiaPagamento
									.setIdSubCategoria(subCategoriaGuiaPagamento
											.getComp_id().getSubcategoria()
											.getId());
						}
					}
					// Verificamos se a esfera de poder foi encontrada
					if (helperGuiaPagamento.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTempGuiaPagamento = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTempGuiaPagamento != null) {
							helperGuiaPagamento.setIdEsfera(clienteTempGuiaPagamento
									.getClienteTipo().getEsferaPoder().getId());
						}
					}

					// Verificar existencia de cliente responsavel
					if (helperGuiaPagamento.getIdTipoClienteResponsavel()
							.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helperGuiaPagamento
									.setIdTipoClienteResponsavel(clienteTemp
											.getClienteTipo().getId());
						}
					}

					// [UC0307] - Obter Indicador de Existência de Hidrômetro
					String indicadorHidrometroString = new Integer(
							getControladorImovel()
									.obterIndicadorExistenciaHidrometroImovel(
											idImovelGuiaPagamento)).toString();
					Short indicadorHidrometro = new Short(
							indicadorHidrometroString);
					// Caso indicador de hidrômetro esteja nulo
					// Seta 2(dois) = NÃO no indicador de
					// hidrômetro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helperGuiaPagamento
							.setIndicadorHidrometro(indicadorHidrometro);


					// Valor das Guias Canceladas
					BigDecimal valorGuiasCanceladas = (BigDecimal) retorno[16];
					if (valorGuiasCanceladas == null) {
						valorGuiasCanceladas = new BigDecimal(0);
					}
					// Quantidade de Guias Canceladas
					Integer quantidadeGuiasCanceladas = (Integer) retorno[17];
					if (quantidadeGuiasCanceladas == null) {
						quantidadeGuiasCanceladas = 0;
					}

					// ///////////////////////////////////////////////////////////////////////////////////
					// informacos iguals
					if (listaSimplificadaReFaturamentoGuiaPagamento.contains(helperGuiaPagamento)) {
						int posicaoGuiaPagamento = listaSimplificadaReFaturamentoGuiaPagamento.indexOf(helperGuiaPagamento);
						ResumoFaturamentoGuiaPagamentoHelper jaCadastradoGuiaPagamento = (ResumoFaturamentoGuiaPagamentoHelper) listaSimplificadaReFaturamentoGuiaPagamento
								.get(posicaoGuiaPagamento);

						// documentos faturados
						jaCadastradoGuiaPagamento.setQuantidadeDocumentosFaturados( 
								jaCadastradoGuiaPagamento.getQuantidadeDocumentosFaturados() + quantidadeGuiasCanceladas);

						// Valor Documentos faturados
						jaCadastradoGuiaPagamento.setValorDocumentosFaturados( 
								jaCadastradoGuiaPagamento.getValorDocumentosFaturados().add(valorGuiasCanceladas));

					} else {
						// Somatorios

						// Valor dos Financiamentos Incluidos Debitos a Cobrar
						helperGuiaPagamento
								.setQuantidadeDocumentosFaturados(quantidadeGuiasCanceladas);

						// Valor dos Financiamentos Cancelados Debitos a Cobrar
						helperGuiaPagamento
								.setValorDocumentosFaturados(valorGuiasCanceladas);

						helperGuiaPagamento
								.setAnoMesReferencia(anoMesReferencia);

						listaSimplificadaReFaturamentoGuiaPagamento
								.add(helperGuiaPagamento);
					}
				}// if instance of de debito a cobrar
			}// for de debito a cobrar
			
			for (int i = 0; i < listaSimplificadaReFaturamentoGuiaPagamento.size(); i++) {
				ResumoFaturamentoGuiaPagamentoHelper helper = (ResumoFaturamentoGuiaPagamentoHelper) listaSimplificadaReFaturamentoGuiaPagamento.get(i);
				
				// Montamos todo o agrupamento necessario
				// Mes ano de referencia
				Integer anoMesReferencia = helper.getAnoMesReferencia();
				
				// Gerencia regional
				GGerenciaRegional gerenciaRegional = null;
				if ( helper.getIdGerenciaRegional() != null ){
					gerenciaRegional = new GGerenciaRegional();
					gerenciaRegional.setId( helper.getIdGerenciaRegional() );
				}
				
				// Unidade de Negocio
				GUnidadeNegocio unidadeNegocio = null;
				if ( helper.getIdUnidadeNegocio() != null ){
					unidadeNegocio = new GUnidadeNegocio();
					unidadeNegocio.setId( helper.getIdUnidadeNegocio() );
				}
				
				// Localidade
				GLocalidade localidade = null;
				if ( helper.getIdLocalidade() != null ){
					localidade = new GLocalidade();
					localidade.setId( helper.getIdLocalidade() );
				}				

				// Elo
				GLocalidade elo = null;
				if (helper.getIdElo() != null ){
					elo = new GLocalidade();
					elo.setId( helper.getIdElo() );
				}
				
				// Setor comercial
				GSetorComercial setorComercial = null;
				if(helper.getIdSetorComercial() != null ){
					setorComercial = new GSetorComercial();
					setorComercial.setId( helper.getIdSetorComercial() );
				}

				// Quadra
				GQuadra quadra = null;
				if (helper.getIdQuadra() != null) {
					quadra = new GQuadra();
					quadra.setId(helper.getIdQuadra());
				}				

				// Codigo do setor comercial
				//Integer codigoSetorComercial = null;
				//if (helper.getCodigoSetorComercial() != null) {
				//	codigoSetorComercial = (helper.getCodigoSetorComercial());
				//}
				
				// Numero da quadra
				//Integer numeroQuadra = null;
				//if (helper.getNumeroQuadra() != null) {
				//	numeroQuadra = (helper.getNumeroQuadra());
				//}

				// Perfil do imovel
				GImovelPerfil imovelPerfil = null;
				if (helper.getIdPerfilImovel() != null) {
					imovelPerfil = new GImovelPerfil();
					imovelPerfil.setId(helper.getIdPerfilImovel());
				}
				
				// Esfera de poder do cliente responsavel
				GEsferaPoder esferaPoder = null;
				if (helper.getIdEsfera() != null) {
					esferaPoder = new GEsferaPoder();
					esferaPoder.setId(helper.getIdEsfera());
				}				
				
				// Tipo do cliente responsavel    helper.getIdTipoClienteResponsavel()
				GClienteTipo clienteTipo = null;
				if ( helper.getIdTipoClienteResponsavel() != null ){
					clienteTipo = new GClienteTipo();
					clienteTipo.setId(1);
				}
				
				// Situacao da ligacao de agua
				GLigacaoAguaSituacao ligacaoAguaSituacao = null;
				if (helper.getIdSituacaoLigacaoAgua() != null) {
					ligacaoAguaSituacao = new GLigacaoAguaSituacao();
					ligacaoAguaSituacao
							.setId(helper.getIdSituacaoLigacaoAgua());
				}

				// Situacao da ligacao de esgoto
				GLigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;
				if (helper.getIdSituacaoLigacaoEsgoto() != null) {
					ligacaoEsgotoSituacao = new GLigacaoEsgotoSituacao();
					ligacaoEsgotoSituacao.setId(helper
							.getIdSituacaoLigacaoEsgoto());
				}
				
				// Categoria
				GCategoria categoria = null;
				if (helper.getIdCategoria() != null) {
					categoria = new GCategoria();
					categoria.setId(helper.getIdCategoria());
				}
				
				// Subcategoria
				GSubcategoria subcategoria = null;
				if (helper.getIdSubCategoria() != null) {					
					subcategoria = new GSubcategoria();
					subcategoria.setId(helper.getIdSubCategoria());
				}		

				// Perfil da ligacao de agua
				GLigacaoAguaPerfil perfilLigacaoAgua = null;
				if (helper.getIdPerfilLigacaoAgua() != null ){
					perfilLigacaoAgua = new GLigacaoAguaPerfil();
					perfilLigacaoAgua.setId( helper.getIdPerfilLigacaoAgua() );
				}
				
				// Perfil da ligacao de esgoto
				GLigacaoEsgotoPerfil perfilLigacaoEsgoto = null;
				if (helper.getIdPerfilLigacaoEsgoto() != null ){
					perfilLigacaoEsgoto = new GLigacaoEsgotoPerfil();
					perfilLigacaoEsgoto.setId( helper.getIdPerfilLigacaoEsgoto() );
				}
				
				// Rota
				GRota rota = null;
				if(helper.getIdRota() != null ){
					rota = new GRota();
					rota.setId( helper.getIdRota() );
					rota.setCodigoRota(helper.getCodigoRota());
				}				
				
				// Ultima Alteracao
				//Date ultimaAlteracao = new Date();
				
				// Empresa
				GEmpresa empresa = null;
				if(helper.getGempresa() != null ){
					empresa = new GEmpresa();
					empresa.setId( helper.getGempresa() );
				}	
				
				GDebitoTipo gerDebitoTipo = null;
				if(helper.getDebitoTipo() != null ){
					gerDebitoTipo = new GDebitoTipo();
					gerDebitoTipo.setId( helper.getDebitoTipo() );
				}	

				Integer qtContasRetificadasInserir = 0; 
				Integer qtContasCanceladasInserir  = 0; 
				BigDecimal vlCanceladoAguaInserir = new BigDecimal (0); 
				BigDecimal vlCanceladoEsgotoInserir =  new BigDecimal (0); 
				Integer voCanceladoAguaInserir = 0; 
				Integer voCanceladoEsgotoInserir = 0; 
				Integer qtContasIncluidasInserir   = 0;
				BigDecimal vlIncluidasAguaInserir =  new BigDecimal (0); 
				BigDecimal vlIncluidasEsgotoInserir =  new BigDecimal (0); 
				Integer voIncluidasAguaInserir = 0;
				Integer voIncluidasEsgotoInserir = 0;
				BigDecimal vlIncluidoDebitosInserir =  new BigDecimal (0); 
				BigDecimal vlCanceladoDebitosInserir =  new BigDecimal (0); 
				BigDecimal vlIncluidoCreditosInserir =  new BigDecimal (0); 
				BigDecimal vlCanceladoCreditosInserir =  new BigDecimal (0); 
				BigDecimal vlIncluidoImpostosInserir =  new BigDecimal (0); 
				BigDecimal vlCanceladoImpostosInserir =  new BigDecimal (0); 
				BigDecimal vlIncluidoGuiasInserir = new BigDecimal (0); 
				Integer qtGuiasIncluidasInserir = 0; 
				BigDecimal vlCanceladoGuiasInserir = helper.getValorDocumentosFaturados(); 
				Integer qtGuiasCanceladasInserir  = helper.getQuantidadeDocumentosFaturados(); 
				
				Integer anoMesReferenciaConta = helper.getAnoMesReferencia(); 
				
				// Ultima Alteracao
				Date ultimaAlteracaogp = new Date();
				
				// Criamos um resumo do Refaturamento
				UnResumoRefaturamento resumoRefaturamentograva = new UnResumoRefaturamento(
						anoMesReferencia			, anoMesReferenciaConta     , gerenciaRegional			, unidadeNegocio,
						localidade					, setorComercial			, quadra, 
						setorComercial.getId()		, quadra.getId()			, imovelPerfil,
						esferaPoder					, clienteTipo				, ligacaoAguaSituacao,
						ligacaoEsgotoSituacao		, categoria					, subcategoria, 
						perfilLigacaoAgua			, perfilLigacaoEsgoto		, qtContasRetificadasInserir, 
						qtContasCanceladasInserir	, vlCanceladoAguaInserir	, vlCanceladoEsgotoInserir,
						voCanceladoAguaInserir		, voCanceladoEsgotoInserir	, qtContasIncluidasInserir,		
						vlIncluidasAguaInserir		, vlIncluidasEsgotoInserir	, voIncluidasAguaInserir, 
						voIncluidasEsgotoInserir	, ultimaAlteracaogp			, elo,
						rota, vlIncluidoImpostosInserir, vlCanceladoImpostosInserir, vlIncluidoGuiasInserir, 
						vlCanceladoGuiasInserir, qtGuiasIncluidasInserir,  vlIncluidoCreditosInserir, 
						vlIncluidoDebitosInserir, qtGuiasCanceladasInserir, vlCanceladoCreditosInserir,
						vlCanceladoDebitosInserir );
				
				//	Adicionamos na tabela ResumoRefaturamento
				this.getControladorBatch().inserirObjetoParaBatchGerencial(resumoRefaturamentograva);
				System.out.println("gravando objeto de GUIA DE PAGAMENTO");
			}// do for lista simplificada
			
		}catch (Exception ex) {
		    logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO FATURAMENTO GUIA DE PAGAMENTO", ex);
			throw new EJBException(ex);
		}
	}

	
	/**
	 * Retorna o valor de controladorCadastro
	 * 
	 * @return O valor de controladorCadastro
	 */
	private ControladorGerencialCadastroLocal getControladorGerencialCadastro() {

		ControladorGerencialCadastroLocalHome localHome = null;
		ControladorGerencialCadastroLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorGerencialCadastroLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_GERENCIAL_CADASTRO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	
	
	
	
    /**
     * [UC0571] - Gerar Resumo do Faturamento
     *
     * @author Bruno Barros
     * @date 18/08/2008
     *
     * @param idSetor
     * @throws ControladorException
     */
    public void gerarResumoFaturamento(int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
        
        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o início do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
        
        try {            
            //FS0001 - Verificar existencia de dados para o ano/mes referencia informado
            repositorioGerencialCadastro.excluirResumo( getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(),
            		UnResumoFaturamento.class.getName(), "referencia", idSetor, false );            
            // SB0001 - Gerar Resumo das Contas
            this.gerarResumoContas( idSetor, idFuncionalidadeIniciada );
            // SB0002 - Gerar Resumo das Guias de Pagamento
            this.gerarResumoGuiasPagamento( idSetor, idFuncionalidadeIniciada );
            // SB0003 - Gerar Resumo para Financiamento
            this.gerarResumoFinanciamento( idSetor, idFuncionalidadeIniciada );
            
            // SB0015 - Gerar Resumo Creditos a Realizar
            this.gerarResumoCreditoARealizar( idSetor, idFuncionalidadeIniciada );
            // SB0017 - Gerar Resumo Guias de Devolução
            this.gerarResumoGuiasDevolucao( idSetor, idFuncionalidadeIniciada );

            // --------------------------------------------------------
            //
            // Registrar o fim da execução da Unidade de Processamento
            //
            // --------------------------------------------------------
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

        } catch (Exception ex) {
            logger.error(" ERRO NO SETOR" + idSetor, ex);
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
            throw new EJBException(ex);
        }        
    }
    
    private void gerarResumoContas(int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
        
        try{
            // Selecionamos as contas            
            Collection<Object[]> colContas = this.repositorioGerencialFaturamento
                .pesquisarContasResumoFaturamento( idSetor );        
            
            Iterator iteContas = colContas.iterator();
            
            List<ResumoFaturamentoHelper> listaResumo = new ArrayList<ResumoFaturamentoHelper>();
    
            // Selecionamos os dados para cada conta selecionada
            int i = 1;
            
            while ( iteContas.hasNext() ){
            	
            	if ( i % 100 == 0 || i == 1 ){
            		System.out.println( "AGRUPANDO CONTA " + i + " DE " + colContas.size() );
            	}
            	
            	++i;
                
                // Pegamos a linha atual
                Object[] linha = (Object[]) iteContas.next();
                
                // SB0004 - Preparar Dados Resumo Agua e Esgoto 
                // Criamos um helper para aguas e esgotos                
                Collection colecaoHelperAguaEsgoto = montarResumoFaturamentoAguaEsgoto( linha );
                
                ResumoFaturamentoHelper helperAguaEsgoto = new ResumoFaturamentoHelper();
                
                // Verificamos se ja existe um helper com as quebras
                // inserido na lista de resumo. Se sim, apenas somamos
                // os valores, senão, colocamos um helper novo na lista
                for (Iterator iterColecaoHelperAguaEsgoto = colecaoHelperAguaEsgoto.iterator(); iterColecaoHelperAguaEsgoto
						.hasNext();) {
					helperAguaEsgoto = (ResumoFaturamentoHelper) iterColecaoHelperAguaEsgoto.next();					
					if ( listaResumo.contains( helperAguaEsgoto ) ){
	                    int posicao = listaResumo.indexOf(helperAguaEsgoto);                    
	                    ResumoFaturamentoHelper jaCadastrado = 
	                        (ResumoFaturamentoHelper) listaResumo.get(posicao); 
	                    
	                    somarValoresParaResumoFaturamentoAguaEsgoto( jaCadastrado, helperAguaEsgoto );                    
	                } else {
	                    listaResumo.add( helperAguaEsgoto );
	                }
				}
 
               // SB0005 - Preparar Dados para debitos cobrados
               // Verificamos se iremos montar um helper para débitos cobrados
               BigDecimal valorDebito = ( BigDecimal ) linha[23];
                
               if ( valorDebito != null && valorDebito.compareTo( new BigDecimal( 0 ) ) > 0 ){
                    Collection<ResumoFaturamentoHelper> colResumosDebitosCobrados = montarResumosFaturamentoDebitosCobrados( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de debito cobrado foram criados, agrupamos cada um deles
                    Iterator iteResumosDebitosCobrados = colResumosDebitosCobrados.iterator();
                    
                    BigDecimal valorDebitosCobrados = new BigDecimal( 0 );
                    
                    while ( iteResumosDebitosCobrados.hasNext() ){
                        ResumoFaturamentoHelper helperDebitosCobrados = ( ResumoFaturamentoHelper ) iteResumosDebitosCobrados.next();
                        
                        valorDebitosCobrados = valorDebitosCobrados.add( helperDebitosCobrados.getValorDocumentosFaturadosOutros() );
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperDebitosCobrados ) ){
                            int posicao = listaResumo.indexOf( helperDebitosCobrados );                    
                            ResumoFaturamentoHelper jaCadastrado = 
                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoDebitosCobrados( jaCadastrado, helperDebitosCobrados );                    
                        } else {
                            listaResumo.add( helperDebitosCobrados );
                        }
                    }
                }
               
                // SB0007 - Preparar os dados no resumo para créditos realizados
                // Verificamos se iremos montar um helper para creditos realizados
                BigDecimal valorCreditosRealizados = ( BigDecimal ) linha[25];
                
                if ( valorCreditosRealizados != null ){
                    Collection<ResumoFaturamentoHelper> colResumosCreditosRealizados = montarResumosFaturamentoCreditoRealizado( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de CreditosRealizados foram criados, agrupamos cada um deles
                    Iterator iteResumosCreditosRealizados = colResumosCreditosRealizados.iterator();
                    
                    while ( iteResumosCreditosRealizados.hasNext() ){
                        ResumoFaturamentoHelper helperCreditosRealizados = ( ResumoFaturamentoHelper ) iteResumosCreditosRealizados.next();
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperCreditosRealizados ) ){
                            int posicao = listaResumo.indexOf( helperCreditosRealizados );                    
                            ResumoFaturamentoHelper jaCadastrado = 
                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoCreditoRealizado( jaCadastrado, helperCreditosRealizados );                    
                        } else {
                            listaResumo.add( helperCreditosRealizados );
                        }                        
                    }
                }
                
                // SB0006 - Preparar os dados no resumo para impostos
                // Verificamos se iremos montar um helper para impostos
                BigDecimal valorImposto = ( BigDecimal ) linha[24];
                
                if ( valorImposto != null && valorImposto.compareTo( new BigDecimal( 0 ) ) > 0 ){
                    Collection<ResumoFaturamentoHelper> colResumosImposto = montarResumosFaturamentoImposto( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de ContaImpostoDebuzido foram criados, agrupamos cada um deles
                    Iterator iteResumosImposto = colResumosImposto.iterator();
                    
                    while ( iteResumosImposto.hasNext() ){
                        ResumoFaturamentoHelper helperImposto = ( ResumoFaturamentoHelper ) iteResumosImposto.next();
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperImposto ) ){
                            int posicao = listaResumo.indexOf( helperImposto );                    
                            ResumoFaturamentoHelper jaCadastrado = 
                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoImposto( jaCadastrado, helperImposto );                    
                        } else {
                            listaResumo.add( helperImposto );
                        }                        
                    }
                }
                // --------------------------------------------
                
                
            }
            
            // Inserimos todos os helpers de resumo
            Iterator iteResumo = listaResumo.iterator();
            
            i = 1;
            
            while ( iteResumo.hasNext() ){
                ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
                
            	if ( i % 100 == 0 || i == 1 ){
            		System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
            	}
            	
            	++i;
            	
                repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
            }              
        } catch ( ErroRepositorioException e ){
            throw new ControladorException( e.getMessage(), e );
        }
    }
    
    /**
     * 
     * [UC0571] - Gerar Resumo do Faturamento
     * SB0004 - Preparar dados do Resumo Agua e Esgotos
     *
     * @author Bruno Barros
     * @date 18/08/2008
     *
     * @param linha - Linha do select que contem os dados da conta
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    private Collection montarResumoFaturamentoAguaEsgoto( Object[] linha ) throws ControladorException, ErroRepositorioException{
        
    	Collection colecaoHelperAguaEsgoto = new ArrayList();
           
        boolean salvouQtConta = false;
        // [UC0306] - Obter Principal Categoria do Imovel
        // pesquisando a categoria
        Imovel imovel = new Imovel( (Integer) linha[21] );
        imovel = 
            this.getControladorImovel()
                .pesquisarImovel(imovel.getId());           
        
        Collection colecaoContaCategoria = this.repositorioFaturamento.pesquisarContaCategoriaResumo((Integer) linha[26]);

        if(colecaoContaCategoria != null && !colecaoContaCategoria.isEmpty()){
        	
        	Iterator iteratorContacategoria = colecaoContaCategoria.iterator();
       
	        while (iteratorContacategoria.hasNext()) {
				ContaCategoria contaCategoria = (ContaCategoria) iteratorContacategoria.next();
				
				ResumoFaturamentoHelper helperAguaEsgoto = new ResumoFaturamentoHelper();
				
				helperAguaEsgoto.setTipoResumo( ResumoFaturamentoHelper.RESUMO_AGUA_ESGOTO );
				helperAguaEsgoto.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
		        helperAguaEsgoto.setIdGerenciaRegional( (Integer) linha[0] );
		        helperAguaEsgoto.setIdUnidadeNegocio( (Integer) linha[1] );
		        helperAguaEsgoto.setCdElo( (Integer) linha[2] );
		        helperAguaEsgoto.setIdLocalidade( (Integer) linha[3] );
		        helperAguaEsgoto.setIdSetorComercial( (Integer) linha[4] );
		        helperAguaEsgoto.setCdSetorComercial( (Integer) linha[5] );
		        helperAguaEsgoto.setIdRota( (Integer) linha[6] );
		        helperAguaEsgoto.setCdRota( (Short) linha[7] );
		        helperAguaEsgoto.setIdQuadra( (Integer) linha[8] );
		        helperAguaEsgoto.setNmQuadra( (Integer) linha[9] );
		        helperAguaEsgoto.setIdPerfilImovel( (Integer) linha[10] );
		        helperAguaEsgoto.setSituacaoLigacaoAgua( (Integer) linha[11] );
		        helperAguaEsgoto.setSituacaoLigacaoEsgoto( (Integer) linha[12] );
		        helperAguaEsgoto.setIdTipoDocumento( 1 );
								
				Categoria categoria = contaCategoria.getComp_id().getCategoria();
				
				helperAguaEsgoto.setIdCategoria( categoria.getId() );
		        // ---------------------------------------------       
			
				Subcategoria subcategoria = contaCategoria.getComp_id().getSubcategoria();
				
				if (subcategoria != null) {
	                helperAguaEsgoto.setIdSubcategoria(
	                        subcategoria.getId() );
	            }
				
		        // ---------------------------------------------
		        
		        // Esfera de Poder do Cliente Conta
		        // SB0010 - Obter dados do cliente da conta
		        // Pesquisamos a esfera de poder do cliente responsavel
		        helperAguaEsgoto.setIdEsferaPoder(
		                this.repositorioGerencialCadastro
		                        .pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
		        // Pesquisamos o tipo de cliente responsavel do imovel
		        helperAguaEsgoto.setIdTipoCliente(
		                this.repositorioGerencialCadastro
		                        .pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
		        
		        // Verificamos se a esfera de poder foi encontrada
		        // para o cliente tipo responsavel, caso nao tenh
		        // pesquisamos pelo cliente usuario
		        if (helperAguaEsgoto.getIdEsferaPoder().equals(0)) {
		            Cliente clienteTemp = 
		                this.getControladorImovel()
		                    .consultarClienteUsuarioImovel(imovel);
		            
		            if (clienteTemp != null) {
		                helperAguaEsgoto.setIdEsferaPoder(
		                        clienteTemp.getClienteTipo().getEsferaPoder().getId() );
		            }
		        }
		        
		        // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
		        // pesquisamos pelo cliente usuario
		        if (helperAguaEsgoto.getIdTipoCliente().equals(0)) {
		            
		            Cliente clienteTemp = this.getControladorImovel()
		                    .consultarClienteUsuarioImovel(imovel);
		            
		            if (clienteTemp != null) {
		                helperAguaEsgoto.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
		            }
		        }
		        // ------------------------------------------------------        
		        helperAguaEsgoto.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
		        helperAguaEsgoto.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
		        helperAguaEsgoto.setIdTarifaConsumo( (Integer) linha [15] );
		        helperAguaEsgoto.setIdGrupoFaturamento( (Integer) linha [16] );
		        helperAguaEsgoto.setIdEmpresa( (Integer) linha [17] );
		
		        // Verificamos a existencia de hidrometro
		        helperAguaEsgoto.setIndHidrometro( 
		                this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );        
		        
		        //Setando os valores de agua e esgoto	        

		        if (contaCategoria.getValorAgua()!= null) {
		        	helperAguaEsgoto.setValorAgua(contaCategoria.getValorAgua());
		        } else {
		        	helperAguaEsgoto.setValorAgua(new BigDecimal(0));
		        }
		        if (contaCategoria.getValorEsgoto() != null){
		        	helperAguaEsgoto.setValorEsgoto(contaCategoria.getValorEsgoto());
		        } else {
		        	helperAguaEsgoto.setValorEsgoto(new BigDecimal(0));
		        }
		        
		          	          
		        // SB0013 - Obter Consumo de Água
   
		        if(contaCategoria.getConsumoAgua()!= null && contaCategoria.getConsumoAgua()>0 ){
		        	if (contaCategoria.getConsumoMinimoAgua() != null){
		        		if (contaCategoria.getConsumoMinimoAgua().compareTo(contaCategoria.getConsumoAgua()) < 0){
		        			helperAguaEsgoto.setVolumeAgua( contaCategoria.getConsumoAgua() );
		        		} else {
		        			helperAguaEsgoto.setVolumeAgua( contaCategoria.getConsumoMinimoAgua() );
		        		}
		        		
		        	} else {
		        		helperAguaEsgoto.setVolumeAgua( contaCategoria.getConsumoAgua() );
		        	}
		        } else {
		        	helperAguaEsgoto.setVolumeAgua( 0 );
		        }
		        
		        if(contaCategoria.getConsumoEsgoto()!= null && contaCategoria.getConsumoEsgoto()>0 ){
		        	if (contaCategoria.getConsumoMinimoEsgoto() != null){
		        		if (contaCategoria.getConsumoMinimoEsgoto().compareTo(contaCategoria.getConsumoEsgoto()) < 0){
		        			helperAguaEsgoto.setVolumeEsgoto( contaCategoria.getConsumoEsgoto() );
		        		} else {
		        			helperAguaEsgoto.setVolumeEsgoto( contaCategoria.getConsumoMinimoEsgoto() );
		        		}
		        		
		        	} else {
		        		helperAguaEsgoto.setVolumeEsgoto( contaCategoria.getConsumoEsgoto() );
		        	}
		        } else {
		        	helperAguaEsgoto.setVolumeEsgoto( 0 );
		        }
		        
		        
		        //helperAguaEsgoto.setQuantidadeEconomiasFaturadas(( (Short) linha[20] ).intValue() );
		        
		        helperAguaEsgoto.setQuantidadeEconomiasFaturadas(new Integer(contaCategoria.getQuantidadeEconomia()));
		        
		        //helperAguaEsgoto.setQuantidadeContasEmitidas( 1 );
		        
		        if (!salvouQtConta)	{
		        	helperAguaEsgoto.setQuantidadeContasEmitidas( 1 );
		        	salvouQtConta = true;
		        } else {
		        	helperAguaEsgoto.setQuantidadeContasEmitidas( 0 );
		        }
		
		        colecaoHelperAguaEsgoto.add(helperAguaEsgoto);
	        }
        }
        return colecaoHelperAguaEsgoto;
        
    }
    
    /**
     * 
     * [UC0571] - Gerar Resumo do Faturamento
     * SB0004 - Preparar dados do Resumo Agua e Esgotos
     *
     * @author Bruno Barros
     * @date 19/08/2008
     *
     * @param objeto - Resumo do faturamento montado anteriormente para o valor de agua 
     * @param linha - Linha do select que contem os dados da conta
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    private Collection<ResumoFaturamentoHelper> montarResumosFaturamentoDebitosCobrados( ResumoFaturamentoHelper objeto, Object[] linha ) throws ControladorException, ErroRepositorioException{        
        
        // Montamos um filtro para selecionar os débitos cobrados da conta
    	boolean somouQuantidade = false;
    	Collection colDebitoCobradoCategoria = repositorioFaturamento.pesquisarDebitoCobradoCategoriaResumo((Integer) linha[26]);
        
    	Integer qtDocumentos = repositorioFaturamento.obterQuantidadeDebitoCobrados((Integer) linha[26]);
        // Com os débitos cobrados selecionados
        Iterator iteDebitoCobradoCategoria = colDebitoCobradoCategoria.iterator();
        Collection<ResumoFaturamentoHelper> colResumo = new ArrayList();
        
        while ( iteDebitoCobradoCategoria.hasNext() ){  
        	DebitoCobradoCategoria debito = ( DebitoCobradoCategoria ) iteDebitoCobradoCategoria.next();
            ResumoFaturamentoHelper helper = preencherDadosBasicosHelperFaturamento( objeto );
            
            // Informamos que esse helper é do tipo DebitoCobrado
            helper.setTipoResumo( ResumoFaturamentoHelper.RESUMO_DEBITOS_COBRADOS );            
            
            helper.setIdItemLancamentoContabil( debito.getDebitoCobrado().getLancamentoItemContabil().getId() );
            helper.setIdTipoDocumento( 1 );
            helper.setIdTipoFinanciamento( debito.getDebitoCobrado().getFinanciamentoTipo().getId() );
            helper.setIdTipoDebito( debito.getDebitoCobrado().getDebitoTipo().getId() );
            helper.setIdCategoria(debito.getCategoria().getId());
            helper.setIdSubcategoria(0);
            
            helper.setValorDocumentosFaturadosOutros( debito.getValorCategoria());
            
            if (!somouQuantidade){
            	helper.setQuantidadeDocumentosFaturadosOutros( qtDocumentos );
            	somouQuantidade = true;
            } else {
            	helper.setQuantidadeDocumentosFaturadosOutros( 0 );
            }
            
            colResumo.add( helper );
        }       

        return colResumo;
    }
    
    /**
     * 
     * [UC0571] - Gerar Resumo Faturamento
     * 
     * Preenche os dados básicos do helper de faturamento
     *
     * @author Bruno Barros
     * @date 19/08/2008
     *
     * @param objeto
     * @return
     */    
    private ResumoFaturamentoHelper preencherDadosBasicosHelperFaturamento( ResumoFaturamentoHelper objeto ){
        ResumoFaturamentoHelper helperDebitosCobrados = new ResumoFaturamentoHelper();
        
        // Setamos as quebras para o helper
        helperDebitosCobrados.setAnoMesFaturamento( objeto.getAnoMesFaturamento() );
        helperDebitosCobrados.setIdGerenciaRegional( objeto.getIdGerenciaRegional() );
        helperDebitosCobrados.setIdUnidadeNegocio( objeto.getIdUnidadeNegocio() );
        helperDebitosCobrados.setCdElo( objeto.getCdElo() );
        helperDebitosCobrados.setIdLocalidade( objeto.getIdLocalidade() );
        helperDebitosCobrados.setIdSetorComercial( objeto.getIdSetorComercial() );
        helperDebitosCobrados.setCdSetorComercial( objeto.getCdSetorComercial() );
        helperDebitosCobrados.setIdRota( objeto.getIdRota() );
        helperDebitosCobrados.setCdRota( objeto.getCdRota() );
        helperDebitosCobrados.setIdQuadra( objeto.getIdQuadra() );
        helperDebitosCobrados.setNmQuadra( objeto.getNmQuadra() );
        helperDebitosCobrados.setIdPerfilImovel( objeto.getIdPerfilImovel() );
        helperDebitosCobrados.setSituacaoLigacaoAgua( objeto.getSituacaoLigacaoAgua() );
        helperDebitosCobrados.setSituacaoLigacaoEsgoto( objeto.getSituacaoLigacaoEsgoto() );
        helperDebitosCobrados.setIdCategoria( objeto.getIdCategoria() );
        helperDebitosCobrados.setIdSubcategoria( objeto.getIdSubcategoria() );
        helperDebitosCobrados.setIdEsferaPoder( objeto.getIdEsferaPoder() );
        helperDebitosCobrados.setIdTipoCliente( objeto.getIdTipoCliente() );
        helperDebitosCobrados.setIdPerfilLigacaoAgua( objeto.getIdPerfilLigacaoAgua() );
        helperDebitosCobrados.setIdPerfilLigacaoEsgoto( objeto.getIdPerfilLigacaoEsgoto() );
        helperDebitosCobrados.setIdTarifaConsumo( objeto.getIdTarifaConsumo() );
        helperDebitosCobrados.setIdGrupoFaturamento( objeto.getIdGrupoFaturamento() );
        helperDebitosCobrados.setIdEmpresa( objeto.getIdEmpresa() );
        helperDebitosCobrados.setIndHidrometro( objeto.getIndHidrometro() );
        
        return helperDebitosCobrados;
    }
    
    /**
     *
     * UC0571 - Gerar Resumo Faturamento
     * Soma os valores relavantes ao resumo de agua e esgoto
     *
     * @author Bruno Barros
     * @date 19/08/2008
     *
     * @param jaCadastrado
     * @param helperAguaEsgoto
     * @return
     */
    private void somarValoresParaResumoFaturamentoAguaEsgoto( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperAguaEsgoto ){
        jaCadastrado.setValorAgua(jaCadastrado.getValorAgua().add( helperAguaEsgoto.getValorAgua() ) );                    
        jaCadastrado.setValorEsgoto(jaCadastrado.getValorEsgoto().add( helperAguaEsgoto.getValorEsgoto() ) );
        jaCadastrado.setVolumeAgua( jaCadastrado.getVolumeAgua() + helperAguaEsgoto.getVolumeAgua() );                    
        jaCadastrado.setVolumeEsgoto(jaCadastrado.getVolumeEsgoto() + helperAguaEsgoto.getVolumeEsgoto() );                    
        jaCadastrado.setQuantidadeEconomiasFaturadas(jaCadastrado.getQuantidadeEconomiasFaturadas() + helperAguaEsgoto.getQuantidadeEconomiasFaturadas() );                    
        jaCadastrado.setQuantidadeContasEmitidas(jaCadastrado.getQuantidadeContasEmitidas() + 1 );
    }
    
    /**
    *
    * UC0571 - Gerar Resumo Faturasmento
    * Soma os valores relavantes ao resumo de debitos cobrados
    *
    * @author Bruno Barros
    * @date 19/08/2008
    *
    * @param jaCadastrado
    * @param helperAguaEsgoto
    * @return
    */
   private void somarValoresParaResumoFaturamentoDebitosCobrados( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperDebitosCobrados ){
       
       jaCadastrado.setValorDocumentosFaturadosOutros( 
               jaCadastrado.getValorDocumentosFaturadosOutros().add( helperDebitosCobrados.getValorDocumentosFaturadosOutros() ) );
       
       jaCadastrado.setQuantidadeDocumentosFaturadosOutros( 
               jaCadastrado.getQuantidadeDocumentosFaturadosOutros() + 1 );
   }
   
   /**
    * 
    * [UC0571] - Gerar Resumo do Faturamento
    * SB0004 - Preparar dados do Resumo de Imposto
    *
    * @author Bruno Barros
    * @date 19/08/2008
    *
    * @param objeto - Resumo do faturamento montado anteriormente para o valor de agua 
    * @param linha - Linha do select que contem os dados da conta
    * @return
    * @throws ControladorException
    * @throws ErroRepositorioException
    */
   private Collection<ResumoFaturamentoHelper> montarResumosFaturamentoImposto( ResumoFaturamentoHelper objeto, Object[] linha ) throws ControladorException, ErroRepositorioException{        
       
       // Montamos um filtro para selecionar os impostos da conta
       FiltroContaImpostosDeduzidos filtro = new FiltroContaImpostosDeduzidos();
       filtro.adicionarParametro( new ParametroSimples( FiltroContaImpostosDeduzidos.CONTA_ID, (Integer) linha[26] ) );
       Collection<ContaImpostosDeduzidos> colImpostoDeduzido = Fachada.getInstancia().pesquisar( filtro, ContaImpostosDeduzidos.class.getName() );
       
       // Com os impostos selecionados
       Iterator iteImpostoDeduzido = colImpostoDeduzido.iterator();
       Collection<ResumoFaturamentoHelper> colResumo = new ArrayList();
       
       while ( iteImpostoDeduzido.hasNext() ){  
           ContaImpostosDeduzidos imposto = ( ContaImpostosDeduzidos ) iteImpostoDeduzido.next();
           ResumoFaturamentoHelper helper = preencherDadosBasicosHelperFaturamento( objeto );
           
           // Informamos que esse helper é do tipo Imposto
           helper.setTipoResumo( ResumoFaturamentoHelper.RESUMO_IMPOSTOS );           
           helper.setIdTipoDocumento( 1 );
           helper.setIdTipoImposto( imposto.getImpostoTipo().getId() );          
           helper.setValorImposto( imposto.getValorImposto() );

           colResumo.add( helper );
       }       

       return colResumo;
   }
   
   /**
   *
   * UC0571 - Gerar Resumo Faturasmento
   * Soma os valores relavantes ao resumo de impostos
   *
   * @author Bruno Barros
   * @date 19/08/2008
   *
   * @param jaCadastrado
   * @param helperAguaEsgoto
   * @return
   */
  private void somarValoresParaResumoFaturamentoImposto( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperDebitosCobrados ){
      jaCadastrado.setValorImposto( 
              jaCadastrado.getValorImposto().add( helperDebitosCobrados.getValorImposto() ) );
  }
  
  /**
   * 
   * [UC0571] - Gerar Resumo do Faturamento
   * SB0004 - Preparar dados do Resumo de Credito Realizados
   *
   * @author Bruno Barros
   * @date 19/08/2008
   *
   * @param objeto - Resumo do faturamento montado anteriormente para o valor de agua 
   * @param linha - Linha do select que contem os dados da conta
   * @return
   * @throws ControladorException
   * @throws ErroRepositorioException
   */
  private Collection<ResumoFaturamentoHelper> montarResumosFaturamentoCreditoRealizado( ResumoFaturamentoHelper objeto, Object[] linha ) throws ControladorException, ErroRepositorioException{        
      
	  Collection colCreditoRealizadoCategoria = repositorioFaturamento.pesquisarCreditoRealizadoCategoriaResumo((Integer) linha[26]);
	  
      // Com os creditos realizados selecionados
      Iterator iteCreditoRealizadoCategoria = colCreditoRealizadoCategoria.iterator();
      Collection<ResumoFaturamentoHelper> colResumo = new ArrayList();
      
      boolean somouQuantidade = false;
      Integer qtDocumentos = repositorioFaturamento.obterQuantidadeCreditosRealizados((Integer) linha[26]);
      
      while ( iteCreditoRealizadoCategoria.hasNext() ){  
          CreditoRealizadoCategoria credito = ( CreditoRealizadoCategoria ) iteCreditoRealizadoCategoria.next();
          ResumoFaturamentoHelper helper = preencherDadosBasicosHelperFaturamento( objeto );
          
          // Informamos que esse helper é do tipo Credito
          helper.setTipoResumo( ResumoFaturamentoHelper.RESUMO_CREDITOS );
          helper.setIdOrigemCredito( credito.getCreditoRealizado().getCreditoOrigem().getId() );
          helper.setIdItemLancamentoContabil( credito.getCreditoRealizado().getLancamentoItemContabil().getId() );
          helper.setIdTipoDocumento( 1 );
          helper.setIdTipoCredito( credito.getCreditoRealizado().getCreditoTipo().getId() );
          helper.setIdCategoria(credito.getCategoria().getId());
          helper.setIdSubcategoria(0);
          
          helper.setValorCreditoRealizado( credito.getValorCategoria() );
          
          if (!somouQuantidade){
        	  helper.setQuantidadeDocumentosFaturadosCredito( qtDocumentos );
          	somouQuantidade = true;
          } else {
        	helper.setQuantidadeDocumentosFaturadosCredito(  0 );
          }
                    
          colResumo.add( helper );
      }       

      return colResumo;
  }
  
  /**
  *
  * UC0571 - Gerar Resumo Faturasmento
  * Soma os valores relavantes ao resumo de Credito Realizado
  *
  * @author Bruno Barros
  * @date 19/08/2008
  *
  * @param jaCadastrado
  * @param helperAguaEsgoto
  * @return
  */
  private void somarValoresParaResumoFaturamentoCreditoRealizado( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperDebitosCobrados ){
     jaCadastrado.setValorCreditoRealizado( 
             jaCadastrado.getValorCreditoRealizado().add( helperDebitosCobrados.getValorCreditoRealizado() ) );
     
     jaCadastrado.setQuantidadeDocumentosFaturadosCredito( 
             jaCadastrado.getQuantidadeDocumentosFaturadosCredito() + 1 );     
  }
  
  /**
   * [UC057] - Gerar Resumo do Faturamento
   *
   * [SB002] - Gerar Resumo de Guias de Pagamento
   *
   * @author Bruno Barros
   * @date 18/08/2008
   *
   * @param idSetor
   * @param idFuncionalidadeIniciada
   * @throws ControladorException
   */
  private void gerarResumoGuiasPagamento(int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
      
      try{
          // Selecionamos as Guias de Pagamento 
    	  
    	  // Verificamos se devemos selecionar pelo setor ou as guias
    	  // sem setor
          Collection<Object[]> colGuias = this.repositorioGerencialFaturamento
              .pesquisarGuiasResumoFaturamento( idSetor );        
          
          Iterator iteGuias = colGuias.iterator();
          
          List<ResumoFaturamentoHelper> listaResumo = new ArrayList<ResumoFaturamentoHelper>();
  
          // Selecionamos os dados para cada conta selecionada         
          int i = 1;
          while ( iteGuias.hasNext() ){
              
        	  if ( i % 100 == 0 || i == 1 ){
        		  System.out.println( "AGRUPANDO GUIA " + i + " DE " + colGuias.size() );
        	  }
        	  
        	  ++i;
              
              // Pegamos a linha atual
              Object[] linha = (Object[]) iteGuias.next();
              
              // SB0008 - Preparar Dados Resumo Guia de Pagamento 
              // Criamos um helper para guias de pagamento
              Collection<ResumoFaturamentoHelper> colHelperGuiaPagamento = montarResumoFaturamentoGuia( linha );
              
              for (Iterator iter = colHelperGuiaPagamento.iterator(); iter
					.hasNext();) {
					ResumoFaturamentoHelper helperGuiaPagamento = (ResumoFaturamentoHelper) iter.next();
					
				
	              // Verificamos se ja existe um helper com as quebras
	              // inserido na lista de resumo. Se sim, apenas somamos
	              // os valores, senão, colocamos um helper novo na lista
	              if ( listaResumo.contains( helperGuiaPagamento ) ){
	                  int posicao = listaResumo.indexOf(helperGuiaPagamento);                    
	                  ResumoFaturamentoHelper jaCadastrado = 
	                      (ResumoFaturamentoHelper) listaResumo.get(posicao); 
	                  
	                  somarValoresParaResumoFaturamentoGuiaPagamento( jaCadastrado, helperGuiaPagamento );                    
	              } else {
	                  listaResumo.add( helperGuiaPagamento );
	              }
              }
              
          }
          
          // Inserimos todos os helpers de resumo
          Iterator iteResumo = listaResumo.iterator();
          i=1;
          while ( iteResumo.hasNext() ){
              ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
              
              if ( i % 100 == 0 || i == 1 ){
                  System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
              }
              
              ++i;
              
              repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
          }             
      } catch ( ErroRepositorioException e ){
          throw new ControladorException( e.getMessage(), e );
      }
  }
  
  /**
   * 
   * [UC0571] - Gerar Resumo do Faturamento
   * SB0002 - Gerar Resumo Guias Pagamento
   *
   * @author Bruno Barros
   * @date 20/08/2008
   *
   * @param linha - Linha do select que contem os dados da guia
   * @return
   * @throws ControladorException
   * @throws ErroRepositorioException
   */
  private Collection montarResumoFaturamentoGuia( Object[] linha ) throws ControladorException, ErroRepositorioException{
      
      boolean contouQuantidade = false;
      
      Collection colResumoFaturamentoHelper = new ArrayList(); 
      
      Collection colGuiaPagamentoCategoria = repositorioFaturamento.pesquisarGuiaPagamentoCategoriaResumo((Integer) linha[25]);
      
      for (Iterator iterGuiaPagamentoCategoria = colGuiaPagamentoCategoria.iterator(); iterGuiaPagamentoCategoria.hasNext();) {
    	  
    	  ResumoFaturamentoHelper helperGuia = new ResumoFaturamentoHelper();
    	  //    	 Informamos que esse helper é do tipo AguaEsgoto
          helperGuia.setTipoResumo( ResumoFaturamentoHelper.RESUMO_GUIA );
    	  GuiaPagamentoCategoria guiaPagamentoCategoria = (GuiaPagamentoCategoria) iterGuiaPagamentoCategoria.next();

    	  // Setamos as quebras para o helper
	      helperGuia.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
	      helperGuia.setIdGerenciaRegional( (Integer) linha[0] );
	      helperGuia.setIdUnidadeNegocio( (Integer) linha[1] );
	      helperGuia.setCdElo( (Integer) linha[2] );
	      helperGuia.setIdLocalidade( (Integer) linha[3] );
	      
	      // Verificamos se o imovel foi informado na guia de pagamento
	      // caso positivo, trazemos os dados da guia, caso negativo
	      // pegamos os dados do imovel
	      if ( (Integer) linha[19] != null ){
	          helperGuia.setIdSetorComercial( (Integer) linha[4] );
	          helperGuia.setCdSetorComercial( (Integer) linha[5] );
	          helperGuia.setIdRota( (Integer) linha[6] );
	          helperGuia.setCdRota( (Short) linha[7] );
	          helperGuia.setIdQuadra( (Integer) linha[8] );
	          helperGuia.setNmQuadra( (Integer) linha[9] );          
	          helperGuia.setIdGrupoFaturamento( (Integer) linha[22] );
	          helperGuia.setIdEmpresa( (Integer) linha[23] );          
	          
	          // [UC0306] - Obter Principal Categoria do Imovel
	          // pesquisando a categoria
	          Imovel imovel = new Imovel( (Integer) linha[19] );
	          
	          helperGuia.setIdCategoria( guiaPagamentoCategoria.getCategoria().getId() );
	          // ---------------------------------------------       
	          
	          // Pesquisamos a subcategoria
	          ImovelSubcategoria subcategoria = null;
	          if (guiaPagamentoCategoria.getCategoria().getId() != null) {
	              // Pesquisando a principal subcategoria
	              subcategoria = this
	                      .getControladorImovel()
	                      .obterPrincipalSubcategoria(guiaPagamentoCategoria.getCategoria().getId(),
	                              imovel.getId());
	
	              if (subcategoria != null) {
	            	  helperGuia.setIdSubcategoria(
	                          subcategoria.getComp_id().getSubcategoria().getId() );
	              }
	          }
	          // ---------------------------------------------
	          
	          // Verificamos a existencia de hidrometro
	          helperGuia.setIndHidrometro( 
	                  this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );          
	      } else {
	          // Como a guia nao possue imovel atribuimos o primeiro setor comercial 
	          // da localidade da guia
	          FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
	          filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID_LOCALIDADE, (Integer) linha[3] ) );
	          filtroSetorComercial.setCampoOrderBy( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL );
	          SetorComercial setorComercial = 
	              (SetorComercial) Fachada.getInstancia().pesquisar( filtroSetorComercial, SetorComercial.class.getName() ).iterator().next();
	          
	          // Setamos o setor comercial no helper
	          helperGuia.setIdSetorComercial( setorComercial.getId() );
	          helperGuia.setCdSetorComercial( setorComercial.getCodigo() );
	          
	          // Com o setor comercial selecionado, selecionamos a primeira quadra
	          FiltroQuadra filtroQuadra = new FiltroQuadra();
	          filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId() ) );
	          filtroQuadra.adicionarCaminhoParaCarregamentoEntidade( "rota.faturamentoGrupo" );
	          filtroQuadra.setCampoOrderBy( FiltroQuadra.ID );
	          Quadra quadra = 
	              (Quadra) Fachada.getInstancia().pesquisar( filtroQuadra, Quadra.class.getName() ).iterator().next();    
	          
	          // Setamos a rota no helper
	          helperGuia.setIdRota( quadra.getRota().getId() );
	          helperGuia.setCdRota( quadra.getRota().getCodigo() );
	          helperGuia.setIdGrupoFaturamento( quadra.getRota().getFaturamentoGrupo().getId() );
	          // Setamos a quadra no helper
	          helperGuia.setIdQuadra( quadra.getId() );
	          helperGuia.setNmQuadra( quadra.getNumeroQuadra() );          
	          helperGuia.setIdEmpresa( quadra.getRota().getEmpresa().getId() );
	          
	          helperGuia.setIdCategoria( 1 );
	          helperGuia.setIdSubcategoria( 10 );
	          helperGuia.setIndHidrometro( 2 );
	      }
	      
	      // Verificamos se existe cliente responsavel para o imovel da guia 
	      if ( linha[21] != null ){
	          helperGuia.setIdTipoCliente( (Integer) linha[21] );
	          helperGuia.setIdEsferaPoder( (Integer) linha[20] );              
	      } else {
	          // Esfera de Poder do Cliente da Guia
	          // SB0011 - Obter dados do cliente da Guia
	          // Pesquisamos a esfera de poder do cliente responsavel
	          helperGuia.setIdEsferaPoder(
	                  this.repositorioGerencialCadastro
	                          .pesquisarEsferaPoderClienteResponsavelImovel( (Integer) linha[19] ) );
	          // Pesquisamos o tipo de cliente responsavel do imovel
	          helperGuia.setIdTipoCliente(
	                  this.repositorioGerencialCadastro
	                          .pesquisarTipoClienteClienteResponsavelImovel( (Integer) linha[19]) );              
	      }      
	
	      helperGuia.setIdPerfilImovel( (Integer) linha[10] );
	      helperGuia.setSituacaoLigacaoAgua( (Integer) linha[11] );
	      helperGuia.setSituacaoLigacaoEsgoto( (Integer) linha[12] );     
	      helperGuia.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
	      helperGuia.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
	      helperGuia.setIdTarifaConsumo( (Integer) linha [15] );
	      
	      helperGuia.setIdItemLancamentoContabil( (Integer) linha [16] );
	      helperGuia.setIdTipoDocumento( 7 );
	      
	      helperGuia.setIdTipoFinanciamento( (Integer) linha [17] );
	      helperGuia.setIdTipoDebito( (Integer) linha [18] );
	      
	      helperGuia.setValorDocumentosFaturadosOutros( guiaPagamentoCategoria.getValorCategoria() );
	      
	      if (!contouQuantidade){
	    	  helperGuia.setQuantidadeDocumentosFaturadosOutros( 1 );
	    	  contouQuantidade = true;
	      } else {
	    	  helperGuia.setQuantidadeDocumentosFaturadosOutros( 0 );
	      }
	      
	      colResumoFaturamentoHelper.add(helperGuia);
      
      }

      return colResumoFaturamentoHelper;
  }
  
 /**
  *
  * UC0571 - Gerar Resumo Faturamento
  * Soma os valores relavantes ao resumo de guias de pagamento
  *
  * @author Bruno Barros
  * @date 21/08/2008
  *
  * @param jaCadastrado
  * @param helperGuiaPagamento
  * @return
  */
 private void somarValoresParaResumoFaturamentoGuiaPagamento( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperGuiaPagamento ){
     jaCadastrado.setValorDocumentosFaturadosOutros(   
             jaCadastrado.getValorDocumentosFaturadosOutros().add( helperGuiaPagamento.getValorDocumentosFaturadosOutros() ) );
     jaCadastrado.setQuantidadeDocumentosFaturadosOutros( jaCadastrado.getQuantidadeDocumentosFaturadosOutros() + 1 );
     
 }

 /**
  * [UC057] - Gerar Resumo do Faturamento
  *
  * [SB003] - Gerar Resumo de Financiamento
  *
  * @author Bruno Barros
  * @date 25/08/2008
  *
  * @param idSetor
  * @param idFuncionalidadeIniciada
  * @throws ControladorException
  */
 private void gerarResumoFinanciamento(int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
     
     try{
         // Selecionamos os debitos a cobrar
         Collection<Object[]> colFinanciamento = this.repositorioGerencialFaturamento
             .pesquisarFinanciamento( idSetor );        
         
         Iterator iteFinanciamento= colFinanciamento.iterator();
         
         List<ResumoFaturamentoHelper> listaResumo = new ArrayList<ResumoFaturamentoHelper>();
 
         // Selecionamos os dados
         int i = 1;
         
         while ( iteFinanciamento.hasNext() ){
        	 
        	 if ( i % 100 == 0 || i == 1){             
        		 System.out.println( "AGRUPANDO FINANCIAMENTO " + i + " DE " + colFinanciamento.size() );
        	 }
        	 
        	 ++i;
             
             // Pegamos a linha atual
             Object[] linha = (Object[]) iteFinanciamento.next();
             
             // SB0009 - Preparar Dados Resumo Financiamento 
             // Criamos um helper para debitos a cobrar
             
             Collection colResumoFaturamentoHelper = montarResumoFinanciamento( linha );
             
             for (Iterator iter = colResumoFaturamentoHelper.iterator(); iter
					.hasNext();) {
				ResumoFaturamentoHelper helperFinanciamento = (ResumoFaturamentoHelper) iter.next();
				
	             // Verificamos se ja existe um helper com as quebras
	             // inserido na lista de resumo. Se sim, apenas somamos
	             // os valores, senão, colocamos um helper novo na lista
	             if ( listaResumo.contains( helperFinanciamento ) ){
	                 int posicao = listaResumo.indexOf(helperFinanciamento);                    
	                 ResumoFaturamentoHelper jaCadastrado = 
	                     (ResumoFaturamentoHelper) listaResumo.get(posicao); 
	                 
	                 somarValoresParaResumoFaturamentoFinanciamento( jaCadastrado, helperFinanciamento );                    
	             } else {
	                 listaResumo.add( helperFinanciamento );
	             }
			} 
    
         }
         
         // Inserimos todos os helpers de resumo
         Iterator iteResumo = listaResumo.iterator();
         i=1;
         while ( iteResumo.hasNext() ){
             ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
             
             if ( i % 100 == 0 || i == 1 ){
            	 System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
             }
             
             ++i;
             
             repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
         }             
     } catch ( ErroRepositorioException e ){
         throw new ControladorException( e.getMessage(), e );
     }
 }
 
 /**
  * 
  * [UC0571] - Gerar Resumo do Faturamento
  * SB0004 - Preparar dados do Resumo Financeiro
  *
  * @author Bruno Barros
  * @date 18/08/2008
  * @alteracao: Ivan Sergio - 18/01/2011 - Ver alteracao do UC referente a data 03/01/2011 (Nelson C.);
  *
  * @param linha - Linha do select que contem os dados da conta
  * @return
  * @throws ControladorException
  * @throws ErroRepositorioException
  */
 	private Collection montarResumoFinanciamento( Object[] linha ) throws ControladorException, ErroRepositorioException{
 		
 		Collection colHelperFinanciamento = new ArrayList();
 		
 		Collection colDebitoACobrarCategoria = repositorioFaturamento.pesquisarDebitoACobrarCategoriaResumo( (Integer) linha[28] );
 		
 		Iterator iterDebitoACobrarCategoria = colDebitoACobrarCategoria.iterator();
 		
 		while (iterDebitoACobrarCategoria.hasNext()) {
 			
			DebitoACobrarCategoria debitoACobrarCategoria = (DebitoACobrarCategoria) iterDebitoACobrarCategoria.next();
		
	 		ResumoFaturamentoHelper helperFinanciamento = new ResumoFaturamentoHelper();
	     
	 		// Informamos que esse helper é do tipo AguaEsgoto
	 		helperFinanciamento.setTipoResumo( ResumoFaturamentoHelper.RESUMO_FINANCIAMENTO );
	     
	 		// Setamos as quebras para o helper
	 		helperFinanciamento.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
	 		helperFinanciamento.setIdGerenciaRegional( (Integer) linha[0] );
	 		helperFinanciamento.setIdUnidadeNegocio( (Integer) linha[1] );
	 		helperFinanciamento.setCdElo( (Integer) linha[2] );
	 		helperFinanciamento.setIdLocalidade( (Integer) linha[3] );
	 		helperFinanciamento.setIdSetorComercial( (Integer) linha[4] );
	 		helperFinanciamento.setCdSetorComercial( (Integer) linha[5] );
	 		helperFinanciamento.setIdRota( (Integer) linha[6] );
	 		helperFinanciamento.setCdRota( (Short) linha[7] );
	 		helperFinanciamento.setIdQuadra( (Integer) linha[8] );
	 		helperFinanciamento.setNmQuadra( (Integer) linha[9] );
	 		helperFinanciamento.setIdPerfilImovel( (Integer) linha[10] );
	 		helperFinanciamento.setSituacaoLigacaoAgua( (Integer) linha[11] );
	 		helperFinanciamento.setSituacaoLigacaoEsgoto( (Integer) linha[12] );
	     
	 		// [UC0306] - Obter Principal Categoria do Imovel
	 		// pesquisando a categoria
	 		Imovel imovel = new Imovel( (Integer) linha[20] );
	 		
	 		helperFinanciamento.setIdCategoria( debitoACobrarCategoria.getCategoria().getId() );
	     
	 		// Pesquisamos a subcategoria
	 		ImovelSubcategoria subcategoria = null;
	 		if (debitoACobrarCategoria.getCategoria() != null) {
	 			// Pesquisando a principal subcategoria
	 			subcategoria = 
	 				this.getControladorImovel().obterPrincipalSubcategoria(
	 					debitoACobrarCategoria.getCategoria().getId(),
	 					imovel.getId());
	
	 			if (subcategoria != null) {
	 				helperFinanciamento.setIdSubcategoria(subcategoria.getComp_id().getSubcategoria().getId() );
	 			} else {
	 				helperFinanciamento.setIdSubcategoria( 0 );
	 			}
	 		}
	     
	 		// Esfera de Poder do Cliente Conta
		    // SB0010 - Obter dados do cliente da conta
		    // Pesquisamos a esfera de poder do cliente responsavel
		    helperFinanciamento.setIdEsferaPoder(
		    	this.repositorioGerencialCadastro.
		    		pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
		     // Pesquisamos o tipo de cliente responsavel do imovel
		     helperFinanciamento.setIdTipoCliente(
		    	this.repositorioGerencialCadastro.
		    		pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
	     
		     // Verificamos se a esfera de poder foi encontrada
		     // para o cliente tipo responsavel, caso nao tenh
		     // pesquisamos pelo cliente usuario
		     if (helperFinanciamento.getIdEsferaPoder().equals(0)) {
		    	 Cliente clienteTemp = 
		    		 this.getControladorImovel().
		    		 	consultarClienteUsuarioImovel(imovel);
	         
		    	 if (clienteTemp != null) {
		    		 helperFinanciamento.setIdEsferaPoder(clienteTemp.getClienteTipo().getEsferaPoder().getId() );
		    	 }
		     }
	     
		     // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
		     // pesquisamos pelo cliente usuario
		     if (helperFinanciamento.getIdTipoCliente().equals(0)) {
	         
		    	 Cliente clienteTemp = 
		    		 this.getControladorImovel().consultarClienteUsuarioImovel(imovel);
	         
		    	 if (clienteTemp != null) {
		    		 helperFinanciamento.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
		    	 }
		     }
	     
		     helperFinanciamento.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
		     helperFinanciamento.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
		     helperFinanciamento.setIdTarifaConsumo( (Integer) linha [15] );
		     helperFinanciamento.setIdGrupoFaturamento( (Integer) linha [16] );
		     helperFinanciamento.setIdEmpresa( (Integer) linha [17] );
	
		     // Verificamos a existencia de hidrometro
		     helperFinanciamento.setIndHidrometro( 
	             this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );
	     
		     helperFinanciamento.setIdItemLancamentoContabil( (Integer) linha [18] );
		     helperFinanciamento.setIdTipoDocumento( 6 );
	     
		     helperFinanciamento.setIdTipoFinanciamento( (Integer) linha[26] );
		     helperFinanciamento.setIdTipoDebito( (Integer) linha [19] );
	     
		     Integer debitoCreditoSituacaoAtual = ConstantesSistema.NUMERO_NAO_INFORMADO;
		     Integer debitoCreditoSituacaoAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
		     Integer parcelamentoGrupo = ConstantesSistema.NUMERO_NAO_INFORMADO;
	     
		     if(linha[22] != null){
		    	 debitoCreditoSituacaoAtual = (Integer) linha[22];
		     }
	     
		     if(linha[23] != null){
		    	 debitoCreditoSituacaoAnterior = (Integer) linha[23];
		     }
	
		     if(linha[27] != null){
		    	 parcelamentoGrupo = (Integer) linha[27];
		     }
		     
		     /*BigDecimal numeroParcelaBonus = BigDecimal.ZERO;
		     
		     if (linha[29] != null) {
		    	 numeroParcelaBonus = (BigDecimal) linha[29];
		     }*/
	     
		     if( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ||
		    		 debitoCreditoSituacaoAnterior.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ) && 
		    		 helperFinanciamento.getIdTipoFinanciamento().equals(FinanciamentoTipo.SERVICO_NORMAL) ){
	    	 
		    	 helperFinanciamento.setValorFinanciadoIncluido( debitoACobrarCategoria.getValorCategoria() );
		     }
	     
		     if( debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.CANCELADA.intValue()  && 
		    		 helperFinanciamento.getIdTipoFinanciamento().equals(FinanciamentoTipo.SERVICO_NORMAL) ){
	
		    	 // Pegamos os valores
		    	 BigDecimal valorDebito = debitoACobrarCategoria.getValorCategoria();
		    	 BigDecimal numeroPrestacaoDebito = new BigDecimal( (Short) linha[24] ); 
		    	 BigDecimal numeroPrestacaoCobradas = new BigDecimal( (Short) linha[25] );
		    	 
		    	 BigDecimal valor = 
		    		 valorDebito.subtract( 
	                     valorDebito.divide( 
	                     numeroPrestacaoDebito,2, BigDecimal.ROUND_DOWN ).multiply(numeroPrestacaoCobradas ) );
	         
		    	 helperFinanciamento.setValorFinanciadoCancelado( valor );         
		     }
	     
		     if( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ||
		    		 debitoCreditoSituacaoAnterior.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ) && 
		    		 parcelamentoGrupo.intValue() == ParcelamentoGrupo.JUROS_COBRADOS.intValue() ){
	    	 
		    	 helperFinanciamento.setValorJurosParcelamento( debitoACobrarCategoria.getValorCategoria() );
		     }
	     
		     if( debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.CANCELADA.intValue() && 
		    		 parcelamentoGrupo.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO ){
	     
		    	 BigDecimal valorDebito = debitoACobrarCategoria.getValorCategoria();
		    	 BigDecimal numeroPrestacaoDebito = new BigDecimal( (Short) linha[24] ); 
		    	 BigDecimal numeroPrestacaoCobradas = new BigDecimal( (Short) linha[25] );
		    	 
		    	 BigDecimal valor = 
		    		 valorDebito.subtract( 
	                     valorDebito.divide( 
	                             numeroPrestacaoDebito,2, BigDecimal.ROUND_DOWN ).multiply( numeroPrestacaoCobradas ) );
	         
		    	 helperFinanciamento.setValorParcelamentosCancelados( valor );     
		     }
		     
		     colHelperFinanciamento.add(helperFinanciamento);
 		}
 		
	     return colHelperFinanciamento;
 	}
 
    /**
     * 
     * UC0571 - Gerar Resumo Faturamento
     * Soma os valores relavantes ao resumo do financimento
     *
     * @author Bruno Barros
     * @date 26/08/2008
     * @alteracao: Ivan Sergio - 18/01/2011 - Ver alteracao do UC referente a data 03/01/2011 (Nelson C.) [SB0009]
     *
     * @param jaCadastrado
     * @param helperFinanciamento
     * @return
     */
    private void somarValoresParaResumoFaturamentoFinanciamento( ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperFinanciamento ){
        jaCadastrado.setValorFinanciadoIncluido( 
                jaCadastrado.getValorFinanciadoIncluido().add( helperFinanciamento.getValorFinanciadoIncluido() ) );
        
        jaCadastrado.setValorFinanciadoCancelado( 
                jaCadastrado.getValorFinanciadoCancelado().add( helperFinanciamento.getValorFinanciadoCancelado() ) );
        
       	jaCadastrado.setValorJurosParcelamento( 
       			jaCadastrado.getValorJurosParcelamento().add( helperFinanciamento.getValorJurosParcelamento() ) );
        	
       	jaCadastrado.setValorParcelamentosCancelados( 
       			jaCadastrado.getValorParcelamentosCancelados().add( helperFinanciamento.getValorParcelamentosCancelados() ) );
    }
    
    /**
     * Método que gera o resumo do ReFaturamento para o OLAP
     * 
     * [UC0572] - Gerar Resumo do ReFaturamento
     * 
     * @author Bruno Barros
     * @param idSetor
     * @param idFuncionalidadeIniciada 
     * @param anoMes 
     * @date 24/11/2008
     * 
     */
    public void gerarResumoReFaturamentoOlap(int idSetor,
            int idFuncionalidadeIniciada, int anoMes) throws ControladorException { 
        
        int idUnidadeIniciada = 0;

        // -------------------------
        //
        // Registrar o início do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
        
        try {            
            //FS0001 - Verificar existencia de dados para o ano/mes referencia informado
            repositorioGerencialCadastro.excluirResumo( 
            		getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(),
            		UnResumoRefaturamento.class.getName(), "anoMesReferencia", idSetor, false );            
            // SB0001 - Gerar Resumo das Contas
//            this.gerarResumoContasReFaturamentoOlap( idSetor, idFuncionalidadeIniciada );
            // SB0002 - Gerar Resumo das Guias de Pagamento
            this.gerarResumoGuiasPagamento( idSetor, idFuncionalidadeIniciada );
            // SB0003 - Gerar Resumo para Financiamento
            this.gerarResumoFinanciamento( idSetor, idFuncionalidadeIniciada );

            // --------------------------------------------------------
            //
            // Registrar o fim da execução da Unidade de Processamento
            //
            // --------------------------------------------------------
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

        } catch (Exception ex) {
            logger.error(" ERRO NO SETOR" + idSetor, ex);
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
            throw new EJBException(ex);
        }          
    }
    
    /**
     * [UC0572] - Gerar Resumo do ReFaturamento
     *
     * [SB001] - Gerar Resumo de Contas
     *
     * @author Bruno Barros
     * @date 24/11/2008
     *
     * @param idSetor
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    /*
    private void gerarResumoContasReFaturamentoOlap(int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
        
        try{
            // Selecionamos as contas            
            Collection<Object[]> colContas = this.repositorioGerencialFaturamento
                .pesquisarContasResumoReFaturamentoOlap( idSetor );        
            
            Iterator iteContas = colContas.iterator();
            
            List<ResumoReFaturamentoOlapHelper> listaResumo = new ArrayList<ResumoReFaturamentoOlapHelper>();
    
            // Selecionamos os dados para cada conta selecionada
            int i = 1;
            
            while ( iteContas.hasNext() ){
                
                if ( i % 100 == 0 || i == 1 ){
                    System.out.println( "AGRUPANDO CONTA " + i + " DE " + colContas.size() );
                }
                
                ++i;
                
                // Pegamos a linha atual
                Object[] linha = (Object[]) iteContas.next();
                
                // Selecionamos os dados básicos
                ResumoReFaturamentoOlapHelper helper = montarHelperResumoReFaturamento( linha );

                Integer idImovel = (Integer) linha[21];
                
                // Caso a situação atual de débito/crédito da conta corresponda a retificada ou
                // a situação anterior de débito/crédito da conta corresponda a retificada
                Integer debitoCreditoSituacaoAtual = (Integer) linha[28];
                Integer debitoCreditoSituacaoAnterior = (Integer) linha[29];
                boolean existeContaCanceladaRetificacao = false;
                boolean existeContaRetificada = false;                
                
                if ( debitoCreditoSituacaoAtual.equals( DebitoCreditoSituacao.RETIFICADA ) || 
                     debitoCreditoSituacaoAnterior.equals( DebitoCreditoSituacao.RETIFICADA ) ){
                    // Atribuir 1 (SIM) ao indicador de existencia da conta cancelada por retificação ???????                    
                    existeContaCanceladaRetificacao = true;
                    
                    // Obtemos os dados da conta cancelada
                    Collection<Object[]> colContaCanceladaRetificacao = this.repositorioGerencialFaturamento.pesquisarContaCanceladaRetificacao( idImovel );
                    
                    // Caso não exista conta cancelada por retificação, atribuir o valor 2 ao indicador de conta cancelada por retificação
                    if ( colContaCanceladaRetificacao == null || colContaCanceladaRetificacao.size() == 0 ){
                        existeContaCanceladaRetificacao = false;
                    } else {
                        // Pegamos os valores da conta cancelada por retificação e subtraimos da conta retificada
                        Object[] linhaContaRetificada = colContaCanceladaRetificacao.iterator().next();
                        
                        preencherValoresRetificados( helper, linha, linhaContaRetificada );                        
                    }
                // Caso a situação atual de débito/crédito da conta corresponda a cancelada por retificação                    
                } else if ( debitoCreditoSituacaoAtual.equals( DebitoCreditoSituacao.CANCELADA_POR_RETIFICACAO ) ){
                    existeContaRetificada = true;
                    
                    // Obtemos os dados da conta retificada
                    Collection<Object[]> colContaRetificada = this.repositorioGerencialFaturamento.pesquisarContaRetificada( idImovel );
                    
                    // Caso não exista conta retificada
                    if ( colContaRetificada == null || colContaRetificada.size() == 0 ){
                        existeContaRetificada = false;
                    }           
                }
                
                // [FS0002] - Verificar existencia do resumo para conta
                if ( listaResumo.contains( helper ) ){        
                    // Selecionamos o helper que já está na coleção                    
                    int posicao = listaResumo.indexOf(helper);                    
                    ResumoReFaturamentoOlapHelper jaCadastrado = 
                        (ResumoReFaturamentoOlapHelper) listaResumo.get(posicao);
                    
                    // Caso a situação atual de débito/crédito da conta corresponda a retificada ou
                    // a situação anterior de débito/crédito da conta corresponda a retificada
                    if ( debitoCreditoSituacaoAtual.equals( DebitoCreditoSituacao.RETIFICADA ) || 
                         debitoCreditoSituacaoAnterior.equals( DebitoCreditoSituacao.RETIFICADA ) ){
                           // Acumala a quantidade de contas retificadas
                           jaCadastrado.setQuantidadeContasRetificadas( 
                                   jaCadastrado.getQuantidadeContasRetificadas() + 1 );
                           
                           // Verificamos se existe conta cancelada por retificação
                           if ( existeContaCanceladaRetificacao ){
//                               somarValoresRetificados( jaCadastrado, helper );
                           }
                    } else {
                       
                    }
                    
                } else {
                    
                }
                
                // Acumular os valores da conta do resumo de refaturamento

                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                // Criamos um helper para aguas e esgotos
                ResumoFaturamentoHelper helperAguaEsgoto = montarResumoFaturamentoAguaEsgoto( linha );
                
                // Verificamos se ja existe um helper com as quebras
                // inserido na lista de resumo. Se sim, apenas somamos
                // os valores, senão, colocamos um helper novo na lista
//                if ( listaResumo.contains( helperAguaEsgoto ) ){
//                    int posicao = listaResumo.indexOf(helperAguaEsgoto);                    
//                    ResumoFaturamentoHelper jaCadastrado = 
//                        (ResumoFaturamentoHelper) listaResumo.get(posicao); 
//                    
//                    somarValoresParaResumoFaturamentoAguaEsgoto( jaCadastrado, helperAguaEsgoto );                    
//                } else {
//                    listaResumo.add( helperAguaEsgoto );
//                }
                // --------------------------------------------
                
                // SB0005 - Preparar Dados para debitos cobrados
                // Verificamos se iremos montar um helper para débitos cobrados
                BigDecimal valorDebito = ( BigDecimal ) linha[23];
                
//                if ( valorDebito != null && valorDebito.compareTo( new BigDecimal( 0 ) ) > 0 ){
//                    Collection<ResumoFaturamentoHelper> colResumosDebitosCobrados = montarResumosFaturamentoDebitosCobrados( helperAguaEsgoto, linha );
//                    
//                    // Agora que todos os objetos de debito cobrado foram criados, agrupamos cada um deles
//                    Iterator iteResumosDebitosCobrados = colResumosDebitosCobrados.iterator();
//                    
//                    BigDecimal valorDebitosCobrados = new BigDecimal( 0 );
//                    
//                    while ( iteResumosDebitosCobrados.hasNext() ){
//                        ResumoFaturamentoHelper helperDebitosCobrados = ( ResumoFaturamentoHelper ) iteResumosDebitosCobrados.next();
//                        
//                        valorDebitosCobrados = valorDebitosCobrados.add( helperDebitosCobrados.getValorDocumentosFaturadosOutros() );
//                        
//                        // Verificamos se o resumo em questão ja existe na coleção de resumos
//                        if ( listaResumo.contains( helperDebitosCobrados ) ){
//                            int posicao = listaResumo.indexOf( helperDebitosCobrados );                    
//                            ResumoFaturamentoHelper jaCadastrado = 
//                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
//                            
//                            somarValoresParaResumoFaturamentoDebitosCobrados( jaCadastrado, helperDebitosCobrados );                    
//                        } else {
//                            listaResumo.add( helperDebitosCobrados );
//                        }
//                    }
//                }
                // --------------------------------------------
                
                // SB0006 - Preparar os dados no resumo para impostos
                // Verificamos se iremos montar um helper para impostos
                BigDecimal valorImposto = ( BigDecimal ) linha[24];
                
//                if ( valorImposto != null && valorImposto.compareTo( new BigDecimal( 0 ) ) > 0 ){
//                    Collection<ResumoFaturamentoHelper> colResumosImposto = montarResumosFaturamentoImposto( helperAguaEsgoto, linha );
//                    
//                    // Agora que todos os objetos de ContaImpostoDebuzido foram criados, agrupamos cada um deles
//                    Iterator iteResumosImposto = colResumosImposto.iterator();
//                    
//                    while ( iteResumosImposto.hasNext() ){
//                        ResumoFaturamentoHelper helperImposto = ( ResumoFaturamentoHelper ) iteResumosImposto.next();
//                        
//                        // Verificamos se o resumo em questão ja existe na coleção de resumos
//                        if ( listaResumo.contains( helperImposto ) ){
//                            int posicao = listaResumo.indexOf( helperImposto );                    
//                            ResumoFaturamentoHelper jaCadastrado = 
//                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
//                            
//                            somarValoresParaResumoFaturamentoImposto( jaCadastrado, helperImposto );                    
//                        } else {
//                            listaResumo.add( helperImposto );
//                        }                        
//                    }
//                }
                // --------------------------------------------
                
                // SB0007 - Preparar os dados no resumo para créditos realizados
                // Verificamos se iremos montar um helper para creditos realizados
                BigDecimal valorCreditosRealizados = ( BigDecimal ) linha[25];
                
//                if ( valorCreditosRealizados != null ){
//                    Collection<ResumoFaturamentoHelper> colResumosCreditosRealizados = montarResumosFaturamentoCreditoRealizado( helperAguaEsgoto, linha );
//                    
//                    // Agora que todos os objetos de CreditosRealizados foram criados, agrupamos cada um deles
//                    Iterator iteResumosCreditosRealizados = colResumosCreditosRealizados.iterator();
//                    
//                    while ( iteResumosCreditosRealizados.hasNext() ){
//                        ResumoFaturamentoHelper helperCreditosRealizados = ( ResumoFaturamentoHelper ) iteResumosCreditosRealizados.next();
//                        
//                        // Verificamos se o resumo em questão ja existe na coleção de resumos
//                        if ( listaResumo.contains( helperCreditosRealizados ) ){
//                            int posicao = listaResumo.indexOf( helperCreditosRealizados );                    
//                            ResumoFaturamentoHelper jaCadastrado = 
//                                (ResumoFaturamentoHelper) listaResumo.get(posicao); 
//                            
//                            somarValoresParaResumoFaturamentoCreditoRealizado( jaCadastrado, helperCreditosRealizados );                    
//                        } else {
//                            listaResumo.add( helperCreditosRealizados );
//                        }                        
//                    }
//                }
                // --------------------------------------------
            }
            
            // Inserimos todos os helpers de resumo
            Iterator iteResumo = listaResumo.iterator();
            
            i = 1;
            
            while ( iteResumo.hasNext() ){
                ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
                
                if ( i % 100 == 0 || i == 1 ){
                    System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
                }
                
                ++i;
                
                repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
            }              
        } catch ( ErroRepositorioException e ){
            throw new ControladorException( e.getMessage(), e );
        }
    }
    */
    
    /**
     * 
     * [UC0571] - Gerar Resumo do Faturamento
     * SB0004 - Preparar dados do Resumo Agua e Esgotos
     *
     * @author Bruno Barros
     * @date 18/08/2008
     *
     * @param linha - Linha do select que contem os dados da conta
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    /*
    private ResumoReFaturamentoOlapHelper montarHelperResumoReFaturamento( Object[] linha ) throws ControladorException, ErroRepositorioException{
        
        ResumoReFaturamentoOlapHelper helper = new ResumoReFaturamentoOlapHelper();
        
        
        // Setamos as quebras para o helper
        helper.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
        helper.setIdGerenciaRegional( (Integer) linha[0] );
        helper.setIdUnidadeNegocio( (Integer) linha[1] );
        helper.setCdElo( (Integer) linha[2] );
        helper.setIdLocalidade( (Integer) linha[3] );
        helper.setIdSetorComercial( (Integer) linha[4] );
        helper.setCdSetorComercial( (Integer) linha[5] );
        helper.setIdRota( (Integer) linha[6] );
        helper.setCdRota( (Short) linha[7] );
        helper.setIdQuadra( (Integer) linha[8] );
        helper.setNmQuadra( (Integer) linha[9] );
        helper.setIdPerfilImovel( (Integer) linha[10] );
        helper.setSituacaoLigacaoAgua( (Integer) linha[11] );
        helper.setSituacaoLigacaoEsgoto( (Integer) linha[12] );
        
        // [UC0306] - Obter Principal Categoria do Imovel
        // pesquisando a categoria
        Imovel imovel = new Imovel( (Integer) linha[21] );
        imovel = 
            this.getControladorImovel()
                .pesquisarImovel(imovel.getId());           
        
        Categoria categoria = 
            this.getControladorImovel().obterPrincipalCategoriaImovel( imovel.getId() );
        helper.setIdCategoria( categoria.getId() );
        // ---------------------------------------------       
        
        // Pesquisamos a subcategoria
        ImovelSubcategoria subcategoria = null;
        if (categoria != null) {
            // Pesquisando a principal subcategoria
            subcategoria = this
                    .getControladorImovel()
                    .obterPrincipalSubcategoria(categoria.getId(),
                            imovel.getId());

            if (subcategoria != null) {
                helper.setIdSubcategoria(
                        subcategoria.getComp_id().getSubcategoria().getId() );
            }
        }
        // ---------------------------------------------
        
        // Esfera de Poder do Cliente Conta
        // SB0010 - Obter dados do cliente da conta
        // Pesquisamos a esfera de poder do cliente responsavel
        helper.setIdEsferaPoder(
                this.repositorioGerencialCadastro
                        .pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
        // Pesquisamos o tipo de cliente responsavel do imovel
        helper.setIdTipoCliente(
                this.repositorioGerencialCadastro
                        .pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
        
        // Verificamos se a esfera de poder foi encontrada
        // para o cliente tipo responsavel, caso nao tenh
        // pesquisamos pelo cliente usuario
        if (helper.getIdEsferaPoder().equals(0)) {
            Cliente clienteTemp = 
                this.getControladorImovel()
                    .consultarClienteUsuarioImovel(imovel);
            
            if (clienteTemp != null) {
                helper.setIdEsferaPoder(
                        clienteTemp.getClienteTipo().getEsferaPoder().getId() );
            }
        }
        
        // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
        // pesquisamos pelo cliente usuario
        if (helper.getIdTipoCliente().equals(0)) {
            
            Cliente clienteTemp = this.getControladorImovel()
                    .consultarClienteUsuarioImovel(imovel);
            
            if (clienteTemp != null) {
                helper.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
            }
        }
        // ------------------------------------------------------        
        helper.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
        helper.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
        helper.setIdTarifaConsumo( (Integer) linha [15] );
        helper.setIdGrupoFaturamento( (Integer) linha [16] );

        // Verificamos a existencia de hidrometro
        helper.setIndHidrometro( 
                this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );        

        return helper;
    }
    */
    
    /**
     * 
     * UC0572 - Gerar Resumo ReFaturamento
     * Soma os valores das contas retificadas
     *
     * @author Bruno Barros
     * @date 26/08/2008
     *
     * @param jaCadastrado
     * @param helperFinanciamento
     * @return
     */
    /*
    private void preencherValoresRetificados( ResumoReFaturamentoOlapHelper helper, Object[] contaRetificada, Object[] contaCanceladaRetificacao ){
        // Selecionamos os valores de cada conta
        BigDecimal valorAguaContaRetificada = ( BigDecimal ) contaRetificada[ 18 ];
        BigDecimal valorAguaContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 0 ];
        
        BigDecimal valorEsgotoContaRetificada = ( BigDecimal ) contaRetificada[ 19 ];
        BigDecimal valorEsgotoContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 1 ];
        
        BigDecimal valorDebitoContaRetificada = ( BigDecimal ) contaRetificada[ 23 ];
        BigDecimal valorDebitoContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 2 ];
        
        BigDecimal valorCreditoContaRetificada = ( BigDecimal ) contaRetificada[ 25 ];
        BigDecimal valorCreditoContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 3 ];
        
        BigDecimal valorImpostoContaRetificada = ( BigDecimal ) contaRetificada[ 24 ];
        BigDecimal valorImpostoContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 4 ];
        
        BigDecimal consumoAguaContaRetificada = ( BigDecimal ) contaRetificada[ 22 ];
        BigDecimal consumoAguaContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 5 ];
        
        BigDecimal consumoEsgotoContaRetificada = ( BigDecimal ) contaRetificada[ 27 ];
        BigDecimal consumoEsgotoContaCanceladaRetificacao = ( BigDecimal ) contaRetificada[ 6 ];
        
        helper.setValorAguaRetificado( valorAguaContaCanceladaRetificacao.subtract( valorAguaContaRetificada ) );
        helper.setValorEsgotoRetificado( valorEsgotoContaCanceladaRetificacao.subtract( valorEsgotoContaRetificada ) );
        helper.setValorDebitoRetificado( valorDebitoContaCanceladaRetificacao.subtract( valorDebitoContaRetificada ) );
        helper.setValorCreditoRetificado( valorCreditoContaCanceladaRetificacao.subtract( valorCreditoContaRetificada ) );
        helper.setValorImpostoRetificado( valorImpostoContaCanceladaRetificacao.subtract( valorImpostoContaRetificada ) );
        helper.setConsumoAguaRetificado( consumoAguaContaCanceladaRetificacao.subtract( consumoAguaContaRetificada ) );
        helper.setConsumoEsgotoRetificado( consumoEsgotoContaCanceladaRetificacao.subtract( consumoEsgotoContaRetificada ) );        
    }  
    */
    
    /**
     * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
     *
     * @author Daniel Alves
     * @date 12/04/2010
     *
     * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
     * @throws ControladorException
     */
    public Collection<Object> pesquisarResumoLigacaoEconomiaRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisarResumoLigacaoEconomiaRelatorioDemonstrativo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
    /**
     * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
     *
     * @author Daniel Alves
     * @date 15/04/2010
     *
     * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
     * @throws ControladorException
     */
    public Collection<Object> pesquisarResumoConsumoAguaRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisarResumoConsumoAguaRelatorioDemonstrativo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
    /**
     * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
     *
     * @author Daniel Alves
     * @date 15/04/2010
     *
     * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
     * @throws ControladorException
     */
    public Collection<Object> pesquisaResumoLeituraAnormalidadeRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoLeituraAnormalidadeRelatorioDemonstrativo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
    /**
     * [UC1003] - Emitir Relatorio Demonstrativo Sintetico das Ligacoes         
     *
     * @author Daniel Alves
     * @date 15/04/2010
     *
     * @param FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper
     * @throws ControladorException
     */
    public Collection<Object> pesquisaResumoInstalacaoHidrometroRelatorioDemonstrativo(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoInstalacaoHidrometroRelatorioDemonstrativo(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
    /**
	 * Verifica se existe dados nas tabelas de resumo
	 *
	 * [UC1003] - Gerar Relatorio Demonstrativo Sintetico das Ligacoes
     *
     * @author Daniel Alves
     * @date 16/04/2010
	 *
	 * @param anoMesReferencia
	 * 
	 * @throws ControladorException
	 */
	public void validarDadosRelatorioDomonstrativoSintLigacoes(int anoMesReferencia) 
		throws ControladorException {
		
		try {
			boolean existeDados = this.repositorioGerencial.existeDadosUnResumoParaRelatorioDemonstrativoSintLigacoes(anoMesReferencia);

			if(!existeDados){
				throw new ControladorException("atencao.sem_registros_gerencias");
			}

		} catch (ErroRepositorioException ex) {
			throw new ControladorException("erro.sistema", ex);
		}
	}
	
	/**
     * Gerar Resumo do Faturamento Por Ano
     *
     * @author Fernando Fontelles
     * @date 25/05/2010
     *
     * @param idSetor
     * @throws ControladorException
     */
    public void gerarResumoFaturamentoPorAno(int idSetor, int idFuncionalidadeIniciada) 
    	throws ControladorException{
        
        int idUnidadeIniciada = 0;
        
//      -------------------------
        //
        // Registrar o início do processamento da Unidade de
        // Processamento
        // do Batch
        //
        // -------------------------
        idUnidadeIniciada = getControladorBatch()
                .iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
                        UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
        
        try {            
            //FS0001 - Verificar existencia de dados para o ano/mes referencia informado
            repositorioGerencialCadastro.excluirResumoSQL( 
            		getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento(), 
            		"faturamento.un_resumo_faturamento_ref_2010", 
            		"refa_amreferencia", idSetor, false );            
            
            // Gerar Resumo das Contas Por Ano
            this.gerarResumoContasPorAno( idSetor, idFuncionalidadeIniciada );
            
            // Gerar Resumo das Guias de Pagamento Por Ano
            this.gerarResumoGuiasPagamentoPorAno( idSetor, idFuncionalidadeIniciada );
            
            // Gerar Resumo para Financiamento Por Ano
            this.gerarResumoFinanciamentoPorAno( idSetor, idFuncionalidadeIniciada );

            // --------------------------------------------------------
            //
            // Registrar o fim da execução da Unidade de Processamento
            //
            // --------------------------------------------------------
            getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
                    idUnidadeIniciada, false);

        } catch (Exception ex) {
            logger.error(" ERRO NO SETOR" + idSetor, ex);
            getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
            throw new EJBException(ex);
        }        
    }

    private void gerarResumoContasPorAno(int idSetor, int idFuncionalidadeIniciada) 
    	throws ControladorException{
        
        try{
            // Selecionamos as contas            
            Collection<Object[]> colContas = this.repositorioGerencialFaturamento
                .pesquisarContasResumoFaturamentoPorAno( idSetor );        
            
            Iterator iteContas = colContas.iterator();
            
            List<ResumoFaturamentoPorAnoHelper> listaResumo = new ArrayList<ResumoFaturamentoPorAnoHelper>();
    
            // Selecionamos os dados para cada conta selecionada
            int i = 1;
            
            while ( iteContas.hasNext() ){
            	
            	if ( i % 100 == 0 || i == 1 ){
            		System.out.println( "AGRUPANDO CONTA " + i + " DE " + colContas.size() );
            	}
            	
            	++i;
                
                // Pegamos a linha atual
                Object[] linha = (Object[]) iteContas.next();
                
                // Preparar Dados Resumo Agua e Esgoto 
                // Criamos um helper para aguas e esgotos
                ResumoFaturamentoPorAnoHelper helperAguaEsgoto = montarResumoFaturamentoAguaEsgotoPorAno( linha );
                
                // Verificamos se ja existe um helper com as quebras
                // inserido na lista de resumo. Se sim, apenas somamos
                // os valores, senão, colocamos um helper novo na lista
                if ( listaResumo.contains( helperAguaEsgoto ) ){
                    int posicao = listaResumo.indexOf(helperAguaEsgoto);                    
                    ResumoFaturamentoPorAnoHelper jaCadastrado = 
                        (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
                    
                    somarValoresParaResumoFaturamentoAguaEsgotoPorAno( jaCadastrado, helperAguaEsgoto );                    
                } else {
                    listaResumo.add( helperAguaEsgoto );
                }
                // --------------------------------------------
                
                // Preparar Dados para debitos cobrados
                // Verificamos se iremos montar um helper para débitos cobrados
                BigDecimal valorDebito = ( BigDecimal ) linha[18];
                
                if ( valorDebito != null && valorDebito.compareTo( new BigDecimal( 0 ) ) > 0 ){
                    Collection<ResumoFaturamentoPorAnoHelper> colResumosDebitosCobrados = 
                    	montarResumosFaturamentoDebitosCobradosPorAno( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de debito cobrado foram criados, agrupamos cada um deles
                    Iterator iteResumosDebitosCobrados = colResumosDebitosCobrados.iterator();
                    
                    BigDecimal valorDebitosCobrados = new BigDecimal( 0 );
                    
                    while ( iteResumosDebitosCobrados.hasNext() ){
                        ResumoFaturamentoPorAnoHelper helperDebitosCobrados = 
                        	( ResumoFaturamentoPorAnoHelper ) iteResumosDebitosCobrados.next();
                        
                        valorDebitosCobrados = valorDebitosCobrados.
                        						add( helperDebitosCobrados.getValorDocumentosFaturadosOutros() );
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperDebitosCobrados ) ){
                            int posicao = listaResumo.indexOf( helperDebitosCobrados );                    
                            ResumoFaturamentoPorAnoHelper jaCadastrado = 
                                (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoDebitosCobradosPorAno(
                            		jaCadastrado, helperDebitosCobrados );                    
                        } else {
                            listaResumo.add( helperDebitosCobrados );
                        }
                    }
                }
                // --------------------------------------------
                
                // SB0006 - Preparar os dados no resumo para impostos
                // Verificamos se iremos montar um helper para impostos
                BigDecimal valorImposto = ( BigDecimal ) linha[19];
                
                if ( valorImposto != null && valorImposto.compareTo( new BigDecimal( 0 ) ) > 0 ){
                    Collection<ResumoFaturamentoPorAnoHelper> colResumosImposto = 
                    	montarResumosFaturamentoImpostoPorAno( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de ContaImpostoDebuzido foram criados, agrupamos cada um deles
                    Iterator iteResumosImposto = colResumosImposto.iterator();
                    
                    while ( iteResumosImposto.hasNext() ){
                        ResumoFaturamentoPorAnoHelper helperImposto = 
                        	( ResumoFaturamentoPorAnoHelper ) iteResumosImposto.next();
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperImposto ) ){
                            int posicao = listaResumo.indexOf( helperImposto );                    
                            ResumoFaturamentoPorAnoHelper jaCadastrado = 
                                (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoImpostoPorAno( jaCadastrado, helperImposto );                    
                        } else {
                            listaResumo.add( helperImposto );
                        }                        
                    }
                }
                // --------------------------------------------
                
                // SB0007 - Preparar os dados no resumo para créditos realizados
                // Verificamos se iremos montar um helper para creditos realizados
                BigDecimal valorCreditosRealizados = ( BigDecimal ) linha[20];
                
                if ( valorCreditosRealizados != null ){
                    Collection<ResumoFaturamentoPorAnoHelper> colResumosCreditosRealizados = 
                    	montarResumosFaturamentoCreditoRealizadoPorAno( helperAguaEsgoto, linha );
                    
                    // Agora que todos os objetos de CreditosRealizados foram criados, agrupamos cada um deles
                    Iterator iteResumosCreditosRealizados = colResumosCreditosRealizados.iterator();
                    
                    while ( iteResumosCreditosRealizados.hasNext() ){
                        ResumoFaturamentoPorAnoHelper helperCreditosRealizados = 
                        	( ResumoFaturamentoPorAnoHelper ) iteResumosCreditosRealizados.next();
                        
                        // Verificamos se o resumo em questão ja existe na coleção de resumos
                        if ( listaResumo.contains( helperCreditosRealizados ) ){
                            int posicao = listaResumo.indexOf( helperCreditosRealizados );                    
                            ResumoFaturamentoPorAnoHelper jaCadastrado = 
                                (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
                            
                            somarValoresParaResumoFaturamentoCreditoRealizadoPorAno( 
                            		jaCadastrado, helperCreditosRealizados );                    
                        } else {
                            listaResumo.add( helperCreditosRealizados );
                        }                        
                    }
                }
                // --------------------------------------------
            }
            
            // Inserimos todos os helpers de resumo
            Iterator iteResumo = listaResumo.iterator();
            
            i = 1;
            
            while ( iteResumo.hasNext() ){
                ResumoFaturamentoPorAnoHelper helper = ( ResumoFaturamentoPorAnoHelper ) iteResumo.next();
                
            	if ( i % 100 == 0 || i == 1 ){
            		System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
            	}
            	
            	++i;
                
                repositorioGerencialFaturamento.inserirResumoFaturamentoPorAno( helper );
            }              
        } catch ( ErroRepositorioException e ){
            throw new ControladorException( e.getMessage(), e );
        }
    }
    
    /**
     * 
     * Gerar Resumo do Faturamento Por Ano
     * Preparar dados do Resumo Agua e Esgotos
     *
     * @author Fernando Fontelles Filho
     * @date 25/05/2010
     *
     * @param linha - Linha do select que contem os dados da conta
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    private ResumoFaturamentoPorAnoHelper montarResumoFaturamentoAguaEsgotoPorAno( Object[] linha ) 
    	throws ControladorException, ErroRepositorioException{
        
        ResumoFaturamentoPorAnoHelper helperAguaEsgoto = new ResumoFaturamentoPorAnoHelper();
        
        // Informamos que esse helper é do tipo AguaEsgoto
        helperAguaEsgoto.setTipoResumo( ResumoFaturamentoPorAnoHelper.RESUMO_AGUA_ESGOTO );
        
        // Setamos as quebras para o helper
        helperAguaEsgoto.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
        helperAguaEsgoto.setIdGerenciaRegional( (Integer) linha[0] );
        helperAguaEsgoto.setIdUnidadeNegocio( (Integer) linha[1] );
        helperAguaEsgoto.setCdElo( (Integer) linha[2] );
        helperAguaEsgoto.setIdLocalidade( (Integer) linha[3] );
        helperAguaEsgoto.setIdSetorComercial( (Integer) linha[4] );
        helperAguaEsgoto.setCdSetorComercial( (Integer) linha[5] );
//        helperAguaEsgoto.setIdRota( (Integer) linha[6] );
//        helperAguaEsgoto.setCdRota( (Short) linha[7] );
//        helperAguaEsgoto.setIdQuadra( (Integer) linha[8] );
//        helperAguaEsgoto.setNmQuadra( (Integer) linha[9] );
        helperAguaEsgoto.setIdPerfilImovel( (Integer) linha[6] );
        helperAguaEsgoto.setSituacaoLigacaoAgua( (Integer) linha[7] );
        helperAguaEsgoto.setSituacaoLigacaoEsgoto( (Integer) linha[8] );
        helperAguaEsgoto.setIdTipoDocumento( 1 );
        
        // [UC0306] - Obter Principal Categoria do Imovel
        // pesquisando a categoria
        Imovel imovel = new Imovel( (Integer) linha[16] );
        imovel = 
            this.getControladorImovel()
                .pesquisarImovel(imovel.getId());           
        
        Categoria categoria = 
            this.getControladorImovel().obterPrincipalCategoriaImovel( imovel.getId() );
        helperAguaEsgoto.setIdCategoria( categoria.getId() );
        // ---------------------------------------------       
        
        // Pesquisamos a subcategoria
        ImovelSubcategoria subcategoria = null;
        if (categoria != null) {
            // Pesquisando a principal subcategoria
            subcategoria = this
                    .getControladorImovel()
                    .obterPrincipalSubcategoria(categoria.getId(),
                            imovel.getId());

            if (subcategoria != null) {
                helperAguaEsgoto.setIdSubcategoria(
                        subcategoria.getComp_id().getSubcategoria().getId() );
            }
        }
        // ---------------------------------------------
        
        // Esfera de Poder do Cliente Conta
        // SB0010 - Obter dados do cliente da conta
        // Pesquisamos a esfera de poder do cliente responsavel
        helperAguaEsgoto.setIdEsferaPoder(
                this.repositorioGerencialCadastro
                        .pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
        // Pesquisamos o tipo de cliente responsavel do imovel
        helperAguaEsgoto.setIdTipoCliente(
                this.repositorioGerencialCadastro
                        .pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
        
        // Verificamos se a esfera de poder foi encontrada
        // para o cliente tipo responsavel, caso nao tenh
        // pesquisamos pelo cliente usuario
        if (helperAguaEsgoto.getIdEsferaPoder().equals(0)) {
            Cliente clienteTemp = 
                this.getControladorImovel()
                    .consultarClienteUsuarioImovel(imovel);
            
            if (clienteTemp != null) {
                helperAguaEsgoto.setIdEsferaPoder(
                        clienteTemp.getClienteTipo().getEsferaPoder().getId() );
            }
        }
        
        // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
        // pesquisamos pelo cliente usuario
        if (helperAguaEsgoto.getIdTipoCliente().equals(0)) {
            
            Cliente clienteTemp = this.getControladorImovel()
                    .consultarClienteUsuarioImovel(imovel);
            
            if (clienteTemp != null) {
                helperAguaEsgoto.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
            }
        }
        // ------------------------------------------------------        
        helperAguaEsgoto.setIdPerfilLigacaoAgua( (Integer) ( linha [9] == null ? 0 : linha [9] ) );
        helperAguaEsgoto.setIdPerfilLigacaoEsgoto( (Integer) ( linha [10] == null ? 0 : linha [10] ) );
        helperAguaEsgoto.setIdTarifaConsumo( (Integer) linha [11] );
        helperAguaEsgoto.setIdGrupoFaturamento( (Integer) linha [12] );
//        helperAguaEsgoto.setIdEmpresa( (Integer) linha [17] );

        // Verificamos a existencia de hidrometro
        helperAguaEsgoto.setIndHidrometro( 
                this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );        
        
        helperAguaEsgoto.setValorAgua( (BigDecimal) linha [13] );
        helperAguaEsgoto.setValorEsgoto( (BigDecimal) linha [14] );
        
        try{
          Integer consumoMinimoLigacaoAgua = getControladorMicromedicao().obterConsumoMininoAgua( (Integer) linha[21] );
          
          // SB0013 - Obter Consumo de Água
          if ( linha[17] != null && (Integer) linha[17] > 0 ){
	          if ( consumoMinimoLigacaoAgua.compareTo( (Integer) linha[17] ) < 0 ){
	              helperAguaEsgoto.setVolumeAgua( (Integer) linha[17] );
	          } else {
	              helperAguaEsgoto.setVolumeAgua( consumoMinimoLigacaoAgua );
	          }
          } else {
        	  helperAguaEsgoto.setVolumeAgua( 0 );
          }
          
          Integer consumoMinimoLigacaoEsgoto = 
        	  getControladorMicromedicao().obterConsumoMininoEsgoto( (Integer) linha[21] );
          
          // SB0014 - Obter Consumo de Esgoto
          if ( linha[17] != null && (Integer) linha[17] > 0 ){          
	          if ( consumoMinimoLigacaoEsgoto.compareTo( (Integer) linha[22] ) < 0 ){
	              helperAguaEsgoto.setVolumeEsgoto( (Integer) linha[22] );
	          } else {
	              helperAguaEsgoto.setVolumeEsgoto( consumoMinimoLigacaoEsgoto );
	          }
          } else {
        	  helperAguaEsgoto.setVolumeEsgoto( 0 );
          }
        }
        catch ( NullPointerException e ){
            System.out.println( "IMOVEL: " + imovel.getId() );
            throw e;
        }
        
        helperAguaEsgoto.setQuantidadeEconomiasFaturadas( ( (Short) linha[15] ).intValue() );
        helperAguaEsgoto.setQuantidadeContasEmitidas( 1 );

        return helperAguaEsgoto;
    }
    
    /**
    *
    * Gerar Resumo Faturamento Por Ano
    * Soma os valores relavantes ao resumo de agua e esgoto
    *
    * @author Fernando Fontelles
    * @date 25/05/2010
    *
    * @param jaCadastrado
    * @param helperAguaEsgoto
    * @return
    */
   private void somarValoresParaResumoFaturamentoAguaEsgotoPorAno
   	( ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperAguaEsgoto ){
	   
       jaCadastrado.setValorAgua( 
               jaCadastrado.getValorAgua().add( helperAguaEsgoto.getValorAgua() ) );                    
       jaCadastrado.setValorEsgoto( 
               jaCadastrado.getValorEsgoto().add( helperAguaEsgoto.getValorEsgoto() ) );
       jaCadastrado.setVolumeAgua( 
               jaCadastrado.getVolumeAgua() + helperAguaEsgoto.getVolumeAgua() );                    
       jaCadastrado.setVolumeEsgoto( 
               jaCadastrado.getVolumeEsgoto() + helperAguaEsgoto.getVolumeEsgoto() );                    
       jaCadastrado.setQuantidadeEconomiasFaturadas(
               jaCadastrado.getQuantidadeEconomiasFaturadas() + helperAguaEsgoto.getQuantidadeEconomiasFaturadas() );                    
       jaCadastrado.setQuantidadeEconomiasFaturadas(
               jaCadastrado.getQuantidadeContasEmitidas() + 1 );
       
   }
    
   private Collection<ResumoFaturamentoPorAnoHelper> montarResumosFaturamentoDebitosCobradosPorAno
   ( ResumoFaturamentoPorAnoHelper objeto, Object[] linha )throws ControladorException, ErroRepositorioException{        
       
       // Montamos um filtro para selecionar os débitos cobrados da conta
       FiltroDebitoCobrado filtro = new FiltroDebitoCobrado();
       filtro.adicionarParametro( new ParametroSimples( FiltroDebitoCobrado.CONTA_ID, (Integer) linha[21] ) );
       Collection<DebitoCobrado> colDebitoCobrado = Fachada.getInstancia().pesquisar( filtro, DebitoCobrado.class.getName() );
       
       // Com os débitos cobrados selecionados
       Iterator iteDebitoCobrado = colDebitoCobrado.iterator();
       Collection<ResumoFaturamentoPorAnoHelper> colResumo = new ArrayList();
       
       while ( iteDebitoCobrado.hasNext() ){  
           DebitoCobrado debito = ( DebitoCobrado ) iteDebitoCobrado.next();
           ResumoFaturamentoPorAnoHelper helper = preencherDadosBasicosHelperFaturamentoPorAno( objeto );
           
           // Informamos que esse helper é do tipo DebitoCobrado
           helper.setTipoResumo( ResumoFaturamentoHelper.RESUMO_DEBITOS_COBRADOS );            
           
           helper.setIdItemLancamentoContabil( debito.getLancamentoItemContabil().getId() );
           helper.setIdTipoDocumento( 1 );
           helper.setIdTipoFinanciamento( debito.getFinanciamentoTipo().getId() );
           helper.setIdTipoDebito( debito.getDebitoTipo().getId() );
           
           helper.setValorDocumentosFaturadosOutros( debito.getValorPrestacao() );
           helper.setQuantidadeDocumentosFaturadosOutros( 1 );
           
           colResumo.add( helper );
       }       

       return colResumo;
   }
   
   /**
    * 
    * Gerar Resumo Faturamento Por Ano
    * 
    * Preenche os dados básicos do helper de faturamento
    *
    * @author Fernando Fontelles
    * @date 25/05/2010
    *
    * @param objeto
    * @return
    */    
   private ResumoFaturamentoPorAnoHelper preencherDadosBasicosHelperFaturamentoPorAno
   		( ResumoFaturamentoPorAnoHelper objeto ){
       
	   ResumoFaturamentoPorAnoHelper helperDebitosCobrados = new ResumoFaturamentoPorAnoHelper();
       
       // Setamos as quebras para o helper
       helperDebitosCobrados.setAnoMesFaturamento( objeto.getAnoMesFaturamento() );
       helperDebitosCobrados.setIdGerenciaRegional( objeto.getIdGerenciaRegional() );
       helperDebitosCobrados.setIdUnidadeNegocio( objeto.getIdUnidadeNegocio() );
       helperDebitosCobrados.setCdElo( objeto.getCdElo() );
       helperDebitosCobrados.setIdLocalidade( objeto.getIdLocalidade() );
       helperDebitosCobrados.setIdSetorComercial( objeto.getIdSetorComercial() );
       helperDebitosCobrados.setCdSetorComercial( objeto.getCdSetorComercial() );
       helperDebitosCobrados.setIdPerfilImovel( objeto.getIdPerfilImovel() );
       helperDebitosCobrados.setSituacaoLigacaoAgua( objeto.getSituacaoLigacaoAgua() );
       helperDebitosCobrados.setSituacaoLigacaoEsgoto( objeto.getSituacaoLigacaoEsgoto() );
       helperDebitosCobrados.setIdCategoria( objeto.getIdCategoria() );
       helperDebitosCobrados.setIdSubcategoria( objeto.getIdSubcategoria() );
       helperDebitosCobrados.setIdEsferaPoder( objeto.getIdEsferaPoder() );
       helperDebitosCobrados.setIdTipoCliente( objeto.getIdTipoCliente() );
       helperDebitosCobrados.setIdPerfilLigacaoAgua( objeto.getIdPerfilLigacaoAgua() );
       helperDebitosCobrados.setIdPerfilLigacaoEsgoto( objeto.getIdPerfilLigacaoEsgoto() );
       helperDebitosCobrados.setIdTarifaConsumo( objeto.getIdTarifaConsumo() );
       helperDebitosCobrados.setIdGrupoFaturamento( objeto.getIdGrupoFaturamento() );
       helperDebitosCobrados.setIndHidrometro( objeto.getIndHidrometro() );
       
       return helperDebitosCobrados;
   }
   
  private void somarValoresParaResumoFaturamentoDebitosCobradosPorAno(
		  ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperDebitosCobrados ){
      
      jaCadastrado.setValorDocumentosFaturadosOutros( 
              jaCadastrado.getValorDocumentosFaturadosOutros().
              	add( helperDebitosCobrados.getValorDocumentosFaturadosOutros() ) );
      
      jaCadastrado.setQuantidadeDocumentosFaturadosOutros( 
              jaCadastrado.getQuantidadeDocumentosFaturadosOutros() + 1 );
  }
  
  /**
   * 
   * Gerar Resumo do Faturamento Por Ano
   * Preparar dados do Resumo de Imposto
   *
   * @author Fernando Fontelles
   * @date 25/05/2010
   *
   * @param objeto - Resumo do faturamento montado anteriormente para o valor de agua 
   * @param linha - Linha do select que contem os dados da conta
   * @return
   * @throws ControladorException
   * @throws ErroRepositorioException
   */
  private Collection<ResumoFaturamentoPorAnoHelper> montarResumosFaturamentoImpostoPorAno( 
		  ResumoFaturamentoPorAnoHelper objeto, Object[] linha ) 
		  throws ControladorException, ErroRepositorioException{        
      
      // Montamos um filtro para selecionar os impostos da conta
      FiltroContaImpostosDeduzidos filtro = new FiltroContaImpostosDeduzidos();
      filtro.adicionarParametro( new ParametroSimples( FiltroContaImpostosDeduzidos.CONTA_ID, (Integer) linha[21] ) );
      Collection<ContaImpostosDeduzidos> colImpostoDeduzido = 
    	  Fachada.getInstancia().pesquisar( filtro, ContaImpostosDeduzidos.class.getName() );
      
      // Com os impostos selecionados
      Iterator iteImpostoDeduzido = colImpostoDeduzido.iterator();
      Collection<ResumoFaturamentoPorAnoHelper> colResumo = new ArrayList();
      
      while ( iteImpostoDeduzido.hasNext() ){  
          ContaImpostosDeduzidos imposto = ( ContaImpostosDeduzidos ) iteImpostoDeduzido.next();
          ResumoFaturamentoPorAnoHelper helper = preencherDadosBasicosHelperFaturamentoPorAno( objeto );
          
          // Informamos que esse helper é do tipo Imposto
          helper.setTipoResumo( ResumoFaturamentoPorAnoHelper.RESUMO_IMPOSTOS );           
          helper.setIdTipoDocumento( 1 );
          helper.setIdTipoImposto( imposto.getImpostoTipo().getId() );          
          helper.setValorImposto( imposto.getValorImposto() );

          colResumo.add( helper );
      }       

      return colResumo;
  }
  
  /**
  *
  * Gerar Resumo Faturasmento Por Ano
  * Soma os valores relavantes ao resumo de impostos
  *
  * @author Fernando Fontelles
  * @date 25/05/2010
  *
  * @param jaCadastrado
  * @param helperAguaEsgoto
  * @return
  */
 private void somarValoresParaResumoFaturamentoImpostoPorAno( 
		 ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperDebitosCobrados ){
     jaCadastrado.setValorImposto( 
             jaCadastrado.getValorImposto().add( helperDebitosCobrados.getValorImposto() ) );
 }
  
 /**
  * 
  * Gerar Resumo do Faturamento Por Ano
  * Preparar dados do Resumo de Credito Realizados
  *
  * @author Fernando Fontelles 
  * @date 25/05/2010
  *
  * @param objeto - Resumo do faturamento montado anteriormente para o valor de agua 
  * @param linha - Linha do select que contem os dados da conta
  * @return
  * @throws ControladorException
  * @throws ErroRepositorioException
  */
 private Collection<ResumoFaturamentoPorAnoHelper> montarResumosFaturamentoCreditoRealizadoPorAno( 
		 ResumoFaturamentoPorAnoHelper objeto, Object[] linha ) 
		 throws ControladorException, ErroRepositorioException{        
     
     // Montamos um filtro para selecionar os creditos realizados da conta
     FiltroCreditoRealizado filtro = new FiltroCreditoRealizado();
     filtro.adicionarParametro( new ParametroSimples( FiltroCreditoRealizado.CONTA_ID, (Integer) linha[21] ) );
     Collection<CreditoRealizado> colCreditoRealizado = 
    	 Fachada.getInstancia().pesquisar( filtro, CreditoRealizado.class.getName() );
     
     // Com os creditos realizados selecionados
     Iterator iteCreditoRealizado = colCreditoRealizado.iterator();
     Collection<ResumoFaturamentoPorAnoHelper> colResumo = new ArrayList();
     
     while ( iteCreditoRealizado.hasNext() ){  
         CreditoRealizado credito = ( CreditoRealizado ) iteCreditoRealizado.next();
         ResumoFaturamentoPorAnoHelper helper = preencherDadosBasicosHelperFaturamentoPorAno( objeto );
         
         // Informamos que esse helper é do tipo Credito
         helper.setTipoResumo( ResumoFaturamentoHelper.RESUMO_CREDITOS );
         helper.setIdOrigemCredito( credito.getCreditoOrigem().getId() );
         helper.setIdItemLancamentoContabil( credito.getLancamentoItemContabil().getId() );
         helper.setIdTipoDocumento( 1 );
         helper.setIdTipoCredito( credito.getCreditoTipo().getId() );
         
         helper.setValorCreditoRealizado( credito.getValorCredito() );
         helper.setQuantidadeDocumentosFaturadosCredito( 1 );
                   
         colResumo.add( helper );
     }       

     return colResumo;
 }
 
 /**
 *
 * Gerar Resumo Faturasmento Por Ano
 * Soma os valores relavantes ao resumo de Credito Realizado
 *
 * @author Fernando Fontelles
 * @date 25/05/2010
 *
 * @param jaCadastrado
 * @param helperAguaEsgoto
 * @return
 */
 private void somarValoresParaResumoFaturamentoCreditoRealizadoPorAno( 
		 ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperDebitosCobrados ){
    jaCadastrado.setValorCreditoRealizado( 
            jaCadastrado.getValorCreditoRealizado().add( helperDebitosCobrados.getValorCreditoRealizado() ) );
    
    jaCadastrado.setQuantidadeDocumentosFaturadosCredito( 
            jaCadastrado.getQuantidadeDocumentosFaturadosCredito() + 1 );     
 }
 
 /**
  * Gerar Resumo do Faturamento Por Ano
  *
  * Gerar Resumo de Guias de Pagamento Por Ano
  *
  * @author Fernando Fontelles
  * @date 25/05/2010
  *
  * @param idSetor
  * @param idFuncionalidadeIniciada
  * @throws ControladorException
  */
 private void gerarResumoGuiasPagamentoPorAno(
		 int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
     
     try{
         // Selecionamos as Guias de Pagamento 
   	  
   	  // Verificamos se devemos selecionar pelo setor ou as guias
   	  // sem setor
         Collection<Object[]> colGuias = this.repositorioGerencialFaturamento
             .pesquisarGuiasResumoFaturamentoPorAno( idSetor );        
         
         Iterator iteGuias = colGuias.iterator();
         
         List<ResumoFaturamentoPorAnoHelper> listaResumo = 
        	 new ArrayList<ResumoFaturamentoPorAnoHelper>();
         
 
         // Selecionamos os dados para cada conta selecionada         
         int i = 1;
         while ( iteGuias.hasNext() ){
             
       	  if ( i % 100 == 0 || i == 1 ){
       		  System.out.println( "AGRUPANDO GUIA " + i + " DE " + colGuias.size() );
       	  }
       	  
       	  ++i;
             
             // Pegamos a linha atual
             Object[] linha = (Object[]) iteGuias.next();
             
             // Preparar Dados Resumo Guia de Pagamento 
             // Criamos um helper para guias de pagamento
             ResumoFaturamentoPorAnoHelper helperGuiaPagamento = montarResumoFaturamentoGuiaPorAno( linha );
             
             // Verificamos se ja existe um helper com as quebras
             // inserido na lista de resumo. Se sim, apenas somamos
             // os valores, senão, colocamos um helper novo na lista
             if ( listaResumo.contains( helperGuiaPagamento ) ){
                 int posicao = listaResumo.indexOf(helperGuiaPagamento);                    
                 ResumoFaturamentoPorAnoHelper jaCadastrado = 
                     (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
                 
                 somarValoresParaResumoFaturamentoGuiaPagamentoPorAno( jaCadastrado, helperGuiaPagamento );                    
             } else {
                 listaResumo.add( helperGuiaPagamento );
             }
             // --------------------------------------------
         }
         
         // Inserimos todos os helpers de resumo
         Iterator iteResumo = listaResumo.iterator();
         i=1;
         while ( iteResumo.hasNext() ){
             ResumoFaturamentoPorAnoHelper helper = ( ResumoFaturamentoPorAnoHelper ) iteResumo.next();
             
             if ( i % 100 == 0 || i == 1 ){
                 System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
             }
             
             ++i;
             
             repositorioGerencialFaturamento.inserirResumoFaturamentoPorAno( helper );
         }             
     } catch ( ErroRepositorioException e ){
         throw new ControladorException( e.getMessage(), e );
     }
 }
 
 /**
  * 
  * Gerar Resumo do Faturamento Por Ano
  * Gerar Resumo Guias Pagamento Por Ano
  *
  * @author Fernando Fontelles
  * @date 26/05/2010
  *
  * @param linha - Linha do select que contem os dados da guia
  * @return
  * @throws ControladorException
  * @throws ErroRepositorioException
  */
 private ResumoFaturamentoPorAnoHelper montarResumoFaturamentoGuiaPorAno( Object[] linha ) 
 	throws ControladorException, ErroRepositorioException{
     
     ResumoFaturamentoPorAnoHelper helperGuia = new ResumoFaturamentoPorAnoHelper();
     
     // Informamos que esse helper é do tipo AguaEsgoto
     helperGuia.setTipoResumo( ResumoFaturamentoPorAnoHelper.RESUMO_GUIA );
     
     // Setamos as quebras para o helper
     helperGuia.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
     helperGuia.setIdGerenciaRegional( (Integer) linha[0] );
     helperGuia.setIdUnidadeNegocio( (Integer) linha[1] );
     helperGuia.setCdElo( (Integer) linha[2] );
     helperGuia.setIdLocalidade( (Integer) linha[3] );
     
     // Verificamos se o imovel foi informado na guia de pagamento
     // caso positivo, trazemos os dados da guia, caso negativo
     // pegamos os dados do imovel
     if ( (Integer) linha[15] != null ){
         helperGuia.setIdSetorComercial( (Integer) linha[4] );
         helperGuia.setCdSetorComercial( (Integer) linha[5] );
         
         // [UC0306] - Obter Principal Categoria do Imovel
         // pesquisando a categoria
         Imovel imovel = new Imovel( (Integer) linha[15] );
         Categoria categoria = 
             this.getControladorImovel().obterPrincipalCategoriaImovel( imovel.getId() );
         helperGuia.setIdCategoria( categoria.getId() );
         // ---------------------------------------------       
         
         // Pesquisamos a subcategoria
         ImovelSubcategoria subcategoria = null;
         if (categoria != null) {
             // Pesquisando a principal subcategoria
             subcategoria = this
                     .getControladorImovel()
                     .obterPrincipalSubcategoria(categoria.getId(),
                             imovel.getId());

             if (subcategoria != null) {
           	  helperGuia.setIdSubcategoria(
                         subcategoria.getComp_id().getSubcategoria().getId() );
             }
         }
         // ---------------------------------------------
         
         // Verificamos a existencia de hidrometro
         helperGuia.setIndHidrometro( 
                 this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );          
     } else {
         // Como a guia nao possue imovel atribuimos o primeiro setor comercial 
         // da localidade da guia
         FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
         filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID_LOCALIDADE, (Integer) linha[3] ) );
         filtroSetorComercial.setCampoOrderBy( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL );
         SetorComercial setorComercial = 
             (SetorComercial) Fachada.getInstancia().pesquisar( 
            		 filtroSetorComercial, SetorComercial.class.getName() ).iterator().next();
         
         // Setamos o setor comercial no helper
         helperGuia.setIdSetorComercial( setorComercial.getId() );
         helperGuia.setCdSetorComercial( setorComercial.getCodigo() );         
         helperGuia.setIdCategoria( 1 );
         helperGuia.setIdSubcategoria( 10 );
         helperGuia.setIndHidrometro( 2 );
     }
     
     // Verificamos se existe cliente responsavel para o imovel da guia 
     if ( linha[17] != null ){
         helperGuia.setIdTipoCliente( (Integer) linha[17] );
         helperGuia.setIdEsferaPoder( (Integer) linha[16] );              
     } else {
         // Esfera de Poder do Cliente da Guia
         // SB0011 - Obter dados do cliente da Guia
         // Pesquisamos a esfera de poder do cliente responsavel
         helperGuia.setIdEsferaPoder(
                 this.repositorioGerencialCadastro
                         .pesquisarEsferaPoderClienteResponsavelImovel( (Integer) linha[15] ) );
         // Pesquisamos o tipo de cliente responsavel do imovel
         helperGuia.setIdTipoCliente(
                 this.repositorioGerencialCadastro
                         .pesquisarTipoClienteClienteResponsavelImovel( (Integer) linha[15]) );              
     }      

     helperGuia.setIdPerfilImovel( (Integer) linha[6] );
     helperGuia.setSituacaoLigacaoAgua( (Integer) linha[7] );
     helperGuia.setSituacaoLigacaoEsgoto( (Integer) linha[8] );     
     helperGuia.setIdPerfilLigacaoAgua( (Integer) ( linha [9] == null ? 0 : linha [9] ) );
     helperGuia.setIdPerfilLigacaoEsgoto( (Integer) ( linha [10] == null ? 0 : linha [10] ) );
     helperGuia.setIdTarifaConsumo( (Integer) linha [11] );
     
     helperGuia.setIdItemLancamentoContabil( (Integer) linha [12] );
     helperGuia.setIdTipoDocumento( 7 );
     
     helperGuia.setIdTipoFinanciamento( (Integer) linha [13] );
     helperGuia.setIdTipoDebito( (Integer) linha [14] );
     
     helperGuia.setValorDocumentosFaturadosOutros( (BigDecimal) linha[19] );
     helperGuia.setQuantidadeDocumentosFaturadosOutros( 1 );

     return helperGuia;
 }
 
	 /**
	 *
	 * Gerar Resumo Faturamento Por Ano
	 * Soma os valores relavantes ao resumo de guias de pagamento
	 *
	 * @author Fernando Fontelles
	 * @date 26/05/2010
	 *
	 * @param jaCadastrado
	 * @param helperGuiaPagamento
	 * @return
	 */
	private void somarValoresParaResumoFaturamentoGuiaPagamentoPorAno( 
			ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperGuiaPagamento ){
	    
		jaCadastrado.setValorDocumentosFaturadosOutros(   
	            jaCadastrado.getValorDocumentosFaturadosOutros().add( helperGuiaPagamento.getValorDocumentosFaturadosOutros() ) );
	    jaCadastrado.setQuantidadeDocumentosFaturadosOutros( jaCadastrado.getQuantidadeDocumentosFaturadosOutros() + 1 );
	    
	}
	
	/**
	  * Gerar Resumo do Faturamento Por Ano
	  *
	  * Gerar Resumo de Financiamento
	  *
	  * @author Fernando Fontelles
	  * @date 26/05/2010
	  *
	  * @param idSetor
	  * @param idFuncionalidadeIniciada
	  * @throws ControladorException
	  */
	 private void gerarResumoFinanciamentoPorAno(
			 int idSetor, int idFuncionalidadeIniciada) throws ControladorException{
	     
	     try{
	    	 
	         // Selecionamos os debitos a cobrar
	         Collection<Object[]> colFinanciamento = this.repositorioGerencialFaturamento
	             .pesquisarFinanciamentoPorAno( idSetor );        
	         
	         Iterator iteFinanciamento= colFinanciamento.iterator();
	         
	         List<ResumoFaturamentoPorAnoHelper> listaResumo = new ArrayList<ResumoFaturamentoPorAnoHelper>();
	 
	         // Selecionamos os dados
	         int i = 1;
	         
	         while ( iteFinanciamento.hasNext() ){
	        	 
	        	 if ( i % 100 == 0 || i == 1){             
	        		 System.out.println( "AGRUPANDO FINANCIAMENTO " + i + " DE " + colFinanciamento.size() );
	        	 }
	        	 
	        	 ++i;
	             
	             // Pegamos a linha atual
	             Object[] linha = (Object[]) iteFinanciamento.next();
	             
	             // SB0009 - Preparar Dados Resumo Financiamento 
	             // Criamos um helper para debitos a cobrar
	             ResumoFaturamentoPorAnoHelper helperFinanciamento = montarResumoFinanciamentoPorAno( linha );
	             
	             // Verificamos se ja existe um helper com as quebras
	             // inserido na lista de resumo. Se sim, apenas somamos
	             // os valores, senão, colocamos um helper novo na lista
	             if ( listaResumo.contains( helperFinanciamento ) ){
	                 int posicao = listaResumo.indexOf(helperFinanciamento);                    
	                 ResumoFaturamentoPorAnoHelper jaCadastrado = 
	                     (ResumoFaturamentoPorAnoHelper) listaResumo.get(posicao); 
	                 
	                 somarValoresParaResumoFaturamentoFinanciamentoPorAno( jaCadastrado, helperFinanciamento );                    
	             } else {
	                 listaResumo.add( helperFinanciamento );
	             }
	             // --------------------------------------------
	         }
	         
	         // Inserimos todos os helpers de resumo
	         Iterator iteResumo = listaResumo.iterator();
	         i=1;
	         while ( iteResumo.hasNext() ){
	             ResumoFaturamentoPorAnoHelper helper = ( ResumoFaturamentoPorAnoHelper ) iteResumo.next();
	             
	             if ( i % 100 == 0 || i == 1 ){
	            	 System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
	             }
	             
	             ++i;
	             
	             repositorioGerencialFaturamento.inserirResumoFaturamentoPorAno( helper );
	         }             
	     } catch ( ErroRepositorioException e ){
	         throw new ControladorException( e.getMessage(), e );
	     }
	 }
	 
	 /**
	  * 
	  * Gerar Resumo do Faturamento Por Ano
	  * Preparar dados do Resumo Financeiro
	  *
	  * @author Fernando Fontelles
	  * @date 26/05/2010
	  *
	  * @param linha - Linha do select que contem os dados da conta
	  * @return
	  * @throws ControladorException
	  * @throws ErroRepositorioException
	  */
	 private ResumoFaturamentoPorAnoHelper montarResumoFinanciamentoPorAno( Object[] linha ) 
	 	throws ControladorException, ErroRepositorioException{
	     
	     ResumoFaturamentoPorAnoHelper helperFinanciamento = new ResumoFaturamentoPorAnoHelper();
	     
	     // Informamos que esse helper é do tipo AguaEsgoto
	     helperFinanciamento.setTipoResumo( ResumoFaturamentoPorAnoHelper.RESUMO_FINANCIAMENTO );
	     
	     // Setamos as quebras para o helper
	     helperFinanciamento.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
	     helperFinanciamento.setIdGerenciaRegional( (Integer) linha[0] );
	     helperFinanciamento.setIdUnidadeNegocio( (Integer) linha[1] );
	     helperFinanciamento.setCdElo( (Integer) linha[2] );
	     helperFinanciamento.setIdLocalidade( (Integer) linha[3] );
	     helperFinanciamento.setIdSetorComercial( (Integer) linha[4] );
	     helperFinanciamento.setCdSetorComercial( (Integer) linha[5] );
//	     helperFinanciamento.setIdRota( (Integer) linha[6] );
//	     helperFinanciamento.setCdRota( (Short) linha[7] );
//	     helperFinanciamento.setIdQuadra( (Integer) linha[8] );
//	     helperFinanciamento.setNmQuadra( (Integer) linha[9] );
	     helperFinanciamento.setIdPerfilImovel( (Integer) linha[6] );
	     helperFinanciamento.setSituacaoLigacaoAgua( (Integer) linha[7] );
	     helperFinanciamento.setSituacaoLigacaoEsgoto( (Integer) linha[8] );
	     
	     // [UC0306] - Obter Principal Categoria do Imovel
	     // pesquisando a categoria
	     Imovel imovel = new Imovel( (Integer) linha[15] );
	     Categoria categoria = 
	         this.getControladorImovel().obterPrincipalCategoriaImovel( imovel.getId() );
	     helperFinanciamento.setIdCategoria( categoria.getId() );
	     // ---------------------------------------------       
	     
	     // Pesquisamos a subcategoria
	     ImovelSubcategoria subcategoria = null;
	     if (categoria != null) {
	         // Pesquisando a principal subcategoria
	         subcategoria = this
	                 .getControladorImovel()
	                 .obterPrincipalSubcategoria(categoria.getId(),
	                         imovel.getId());

	         if (subcategoria != null) {
	             helperFinanciamento.setIdSubcategoria(
	                     subcategoria.getComp_id().getSubcategoria().getId() );
	         }
	     }
	     // ---------------------------------------------
	     
	     // Esfera de Poder do Cliente Conta
	     // SB0010 - Obter dados do cliente da conta
	     // Pesquisamos a esfera de poder do cliente responsavel
	     helperFinanciamento.setIdEsferaPoder(
	             this.repositorioGerencialCadastro
	                     .pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
	     // Pesquisamos o tipo de cliente responsavel do imovel
	     helperFinanciamento.setIdTipoCliente(
	             this.repositorioGerencialCadastro
	                     .pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
	     
	     // Verificamos se a esfera de poder foi encontrada
	     // para o cliente tipo responsavel, caso nao tenh
	     // pesquisamos pelo cliente usuario
	     if (helperFinanciamento.getIdEsferaPoder().equals(0)) {
	         Cliente clienteTemp = 
	             this.getControladorImovel()
	                 .consultarClienteUsuarioImovel(imovel);
	         
	         if (clienteTemp != null) {
	             helperFinanciamento.setIdEsferaPoder(
	                     clienteTemp.getClienteTipo().getEsferaPoder().getId() );
	         }
	     }
	     
	     // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
	     // pesquisamos pelo cliente usuario
	     if (helperFinanciamento.getIdTipoCliente().equals(0)) {
	         
	         Cliente clienteTemp = this.getControladorImovel()
	                 .consultarClienteUsuarioImovel(imovel);
	         
	         if (clienteTemp != null) {
	             helperFinanciamento.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
	         }
	     }
	     // ------------------------------------------------------
	     
	     helperFinanciamento.setIdPerfilLigacaoAgua( (Integer) ( linha [9] == null ? 0 : linha [9] ) );
	     helperFinanciamento.setIdPerfilLigacaoEsgoto( (Integer) ( linha [10] == null ? 0 : linha [10] ) );
	     helperFinanciamento.setIdTarifaConsumo( (Integer) linha [11] );
	     
//	     helperFinanciamento.setIdGrupoFaturamento( (Integer) linha [16] );
//	     helperFinanciamento.setIdEmpresa( (Integer) linha [17] );

	     // Verificamos a existencia de hidrometro
	     helperFinanciamento.setIndHidrometro( 
	             this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );
	     
	     helperFinanciamento.setIdItemLancamentoContabil( (Integer) linha [13] );
	     helperFinanciamento.setIdTipoDocumento( 6 );
	     
	     helperFinanciamento.setIdTipoFinanciamento( 1 );
	     helperFinanciamento.setIdTipoDebito( (Integer) linha [14] );    
	     
	     if ( ( linha[17] != null && (Integer) linha[17] == 0 ) || 
	    		 ( linha[18] != null && (Integer) linha[18] == 0 ) ){
	         helperFinanciamento.setValorFinanciadoIncluido( (BigDecimal) linha[16] );
	     } 
	     
	     if ( ( linha[17] != null && (Integer) linha[17] == 3 ) ){
	         // Pegamos os valores
	         BigDecimal valorDebito = (BigDecimal) linha[16];
	         BigDecimal numeroPrestacaoDebito = new BigDecimal( (Short) linha[19] ); 
	         BigDecimal numeroPrestacaoCobradas = new BigDecimal( (Short) linha[20] );
	         
	         BigDecimal valor = 
	             valorDebito.subtract( 
	                     valorDebito.divide( 
	                             numeroPrestacaoDebito,2, BigDecimal.ROUND_HALF_UP ).multiply(
	                                             numeroPrestacaoCobradas ) );
	         
	         helperFinanciamento.setValorFinanciadoCancelado( valor );         
	     }
	     
	     return helperFinanciamento;
	  }
	 
	 /**
	     * 
	     * Gerar Resumo Faturamento Por Ano
	     * Soma os valores relavantes ao resumo do financimento
	     *
	     * @author Fernando Fontelles
	     * @date 26/05/2010
	     *
	     * @param jaCadastrado
	     * @param helperFinanciamento
	     * @return
	     */
	    private void somarValoresParaResumoFaturamentoFinanciamentoPorAno( 
	    		ResumoFaturamentoPorAnoHelper jaCadastrado, ResumoFaturamentoPorAnoHelper helperFinanciamento ){
	        jaCadastrado.setValorFinanciadoIncluido( 
	                jaCadastrado.getValorFinanciadoIncluido().add( helperFinanciamento.getValorFinanciadoIncluido() ) );
	        
	        jaCadastrado.setValorFinanciadoCancelado( 
	                jaCadastrado.getValorFinanciadoCancelado().add( helperFinanciamento.getValorFinanciadoCancelado() ) );
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
	 * @throws ControladorException
	 */
	public void validarDadosUnResumoParaResumoDadosCas(int anoMesReferencia) 
		throws ControladorException {
			
			try {
				boolean existeDados = this.repositorioGerencial.existeDadosUnResumoParaResumoDadosCas(anoMesReferencia);

				if(!existeDados){
					throw new ControladorException("atencao.sem_registros_gerencias");
				}

			} catch (ErroRepositorioException ex) {
				throw new ControladorException("erro.sistema", ex);
			}
		}
	
	
	/**
	 * Pesquisa o Resumo de Ligações Economias para 
	 *  o Resumo com Dados para o CAS
	 *
	 * [UC1017] - Gerar Resumo com Dados para o CAS
	 *
	 * @author Daniel Alves
	 * @date 05/05/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoLigacaoEconomiaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoLigacaoEconomiaResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
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
	 * @date 05/05/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoConsumoAguaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoConsumoAguaResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
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
	 * @date 05/05/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoColetaEsgotoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoColetaEsgotoResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
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
	 * @date 05/05/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoArrecadacaoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoArrecadacaoResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
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
	 * @date 05/05/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoFaturamentoResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoFaturamentoResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
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
    public Collection<Object> pesquisaResumoPendenciaResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoPendenciaResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
	/**
	 * Método que gera o resumo do ReFaturamento Novo
	 * 
	 * [UC0572] - Gerar Resumo do ReFaturamento Novo
	 * 
	 * @author Fernando Fontelles
	 * @param idSetor 
	 * @param anoMes 
	 * @date 29/06/2010
	 * 
	 */
	public void gerarResumoReFaturamentoNovoAntigo(int idSetor,
			int idFuncionalidadeIniciada, int anoMes) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
		
			try {
				// Listas de Controle
				List<ResumoReFaturamentoNovoHelper> listaSimplificadaReFaturamento = new ArrayList<ResumoReFaturamentoNovoHelper>();
				
				SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

				// Indices da paginacao
				int indice = 0;
				int qtRegistros = 500;
				// flag da paginacao 
				boolean flagTerminou = false;
				// contador de paginacao(informativo no debug)
				int count = 0;
				// inicio do processamento
				while(!flagTerminou) {
					
					count++;
					
					//[SB0001 ? Gerar Resumo Contas]
						
					List resumoReFaturamentoContas = this.repositorioGerencialFaturamento.
					getResumoRefaturamentoContas(idSetor , anoMes, indice, qtRegistros);
					
					//[SB0002 ? Gerar Resumo Guias de Pagamento].

					this.gerarResumoReFaturamentoGuiaPagamentoNovo(idSetor, anoMes);
					
					System.out.println("processando resumo refaturamento para o setor:"+idSetor);
					
					for (int i = 0; i < resumoReFaturamentoContas .size(); i++) {
						
						int qtdreg = i + 1;
						
						Object obj = resumoReFaturamentoContas .get(i);

						System.out.println("registro: "+qtdreg+" / "+resumoReFaturamentoContas .size());
		
						if (obj instanceof Object[]) {
								Object[] retorno = (Object[]) obj;
								
								Integer tipoConta = (Integer) retorno[19];
								
								Integer tipoContaAnt = (Integer) retorno[24];
								
								Integer amContabil = (Integer) retorno[20];
								
								
								if (tipoContaAnt == null) {
									tipoContaAnt = 9999;
								}
								
								
								// consumoAgua
								Integer consumoAgua = (Integer) retorno[14];  
								if (consumoAgua == null) {
									consumoAgua = 0;
								}
			
								// consumoEsgoto
								Integer consumoEsgoto = (Integer) retorno[15];
								if (consumoEsgoto == null) {
									consumoEsgoto = 0;
								}
			
								// valorAgua
								BigDecimal valorAgua = (BigDecimal) retorno[16];
								if (valorAgua == null) {
									valorAgua = new BigDecimal(0);
								}
								
								// valorEsgoto
								BigDecimal valorEsgoto = (BigDecimal) retorno[17];
								if (valorEsgoto == null) {
									valorEsgoto = new BigDecimal(0);
								}
								
								Integer anoMesRefConta = (Integer) retorno[18];
								Integer anoMesRef = sistemaParametro.getAnoMesFaturamento();
	
								// Impostos
								BigDecimal valorImpostos = (BigDecimal) retorno[21];  
								if (valorImpostos == null) {
									valorImpostos = new BigDecimal(0);
								}
								
								// Creditos
								BigDecimal valorCreditos = (BigDecimal) retorno[22];  
								if (valorCreditos == null) {
									valorCreditos = new BigDecimal(0);
								}
								
								// Debitos
								BigDecimal valorDebitos = (BigDecimal) retorno[23];  
								if (valorDebitos == null) {
									valorDebitos = new BigDecimal(0);
								}
								
								
								// Montamos um objeto de resumo, com as informacoes do
								// retorno
								ResumoReFaturamentoNovoHelper helper = new ResumoReFaturamentoNovoHelper(
										(Integer) retorno[2],  // Gerencia Regional
										(Integer) retorno[3],  // Unidade de negocio
										(Integer) retorno[4],  // Elo 
										(Integer) retorno[5],  // Localidade
										(Integer) retorno[6],  // Id Setor Comercial
										(Integer) retorno[7],  // Codigo do Setor Comercial
										(Integer) retorno[8], // Perfil do imovel
										(Integer) retorno[9], // Situacao da ligacao da agua
										(Integer) retorno[10], // Situacao da ligacao do esgoto
										(Integer) retorno[11], // Perfil da ligacao do agua
										(Integer) retorno[12]);// Perfil da ligacao do esgoto
								
								Integer consumoTarifa = (Integer) retorno[13];
								helper.setConsumoTarifa(consumoTarifa);
								
								helper.setDocumentoTipo((Integer) retorno[25]);
								
								// AnoMesReferencia
								
								helper.setAnoMesReferencia(anoMesRef);
								helper.setAnoMesReferenciaConta(anoMesRefConta);
								
								// Pesquisamos a esfera de poder do cliente responsavel
								helper.setIdEsfera( this.repositorioGerencialFaturamento.
										pesquisarEsferaPoderClienteResponsavelImovel( (Integer) retorno[1]/*imovel*/ ) );
								
								// Pesquisamos o tipo de cliente responsavel do imovel
								helper.setIdTipoClienteResponsavel( this.repositorioGerencialFaturamento.
										pesquisarTipoClienteClienteResponsavelImovel( (Integer) retorno[1] ) );
								
								// pesquisando a categoria
								// [UC0306] - Obtter principal categoria do imóvel
								Integer idImovel = ( Integer )retorno[1]; // Codigo do imovel que esta sendo processado
								
								Categoria categoria = null;
								
								categoria = this.getControladorImovel()
										.obterPrincipalCategoriaImovel(idImovel);
								
								if (categoria != null) {
									helper.setIdCategoria(categoria.getId());
									
									// Pesquisando a principal subcategoria
									ImovelSubcategoria subcategoria = 
										this.getControladorImovel().obterPrincipalSubcategoria( 
												categoria.getId(), idImovel );
									
									if ( subcategoria != null ){
										helper.setIdSubCategoria( 
												subcategoria.getComp_id().getSubcategoria().getId() );
									}
								}
								
								// Verificamos se a esfera de poder foi encontrada
								// [FS0002] Verificar existencia de cliente responsavel
								if ( helper.getIdEsfera().equals( 0 ) ){
									Imovel imovel = new Imovel();
									imovel.setId(idImovel);
									Cliente clienteTemp = this.getControladorImovel().
												consultarClienteUsuarioImovel( imovel );
									if ( clienteTemp != null ){
									  helper.setIdEsfera( clienteTemp.getClienteTipo().getEsferaPoder().getId() );
									}
								}	
								
								if ( helper.getIdTipoClienteResponsavel().equals( 0 ) ){
									Imovel imovel = new Imovel();
									imovel.setId(idImovel);
									Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
									if ( clienteTemp != null ){
									  helper.setIdTipoClienteResponsavel( clienteTemp.getClienteTipo().getId() );
									}
								}
								
								//Verifica a existencia do Hidrometro
								Boolean existeHidrometro = Fachada.getInstancia().
											verificarExistenciaHidrometroEmLigacaoAgua(idImovel);
								
								Short hidrometro = null;
								if ( existeHidrometro != null && existeHidrometro == true ){
									//Existe Hidrometro
									hidrometro = new Short("1");
									
								}else{
									//Nao Existe Hidrometro
									hidrometro = new Short("2");
									
								}
								helper.setHidrometro(hidrometro);
								
								// se ja existe um objeto igual a ele entao soma os
								// valores e as quantidades ja existentes.
								// um objeto eh igual ao outro se ele tem todos as
								// informacos iguals 
								
								Object[] objCredito = 
									repositorioGerencialFaturamento.getValorCreditoIncluidoCancelado(idImovel, 
										sistemaParametro.getAnoMesFaturamento(), anoMesRefConta);
								
								Object[] objDebito = repositorioGerencialFaturamento.getValorDebitoIncluidoCancelado(idImovel, 
										sistemaParametro.getAnoMesFaturamento(), anoMesRefConta);
								
								BigDecimal valorCreditoConta2ou3ou4 = 
									repositorioGerencialFaturamento.getValorCredito2ou3IncluidoCancelado((Integer) retorno[0]);
								
								BigDecimal valorDebitoConta2ou3ou4 = 
									repositorioGerencialFaturamento.getValorDebito2ou3IncluidoCancelado((Integer) retorno[0]);

								
								BigDecimal valorCreditoCancelado = (BigDecimal) objCredito[0];
								BigDecimal valorCreditoIncluido = (BigDecimal) objCredito[1];
								
								BigDecimal valorDebitoCancelado = (BigDecimal) objDebito[0];
								BigDecimal valorDebitoIncluido = (BigDecimal) objDebito[1];
								
								if (listaSimplificadaReFaturamento.contains(helper)) {
	
									int posicao = listaSimplificadaReFaturamento.indexOf(helper);
									ResumoReFaturamentoNovoHelper jaCadastrado = 
										(ResumoReFaturamentoNovoHelper) listaSimplificadaReFaturamento.get(posicao);
									// Somatorios
									
									// Retificadas
	
									if (tipoConta == 1 || tipoContaAnt == 1 ) {

															
//										jaCadastrado.setQtContasRetificadas(jaCadastrado.getQtContasRetificadas()+1 );
										
										List valoresContaRetificada = this.repositorioGerencialFaturamento
										.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento() , 0 );
										
	//									valor Agua retificado
										BigDecimal valorRetificadoAgua = new BigDecimal(0);
										
										// valor Esgoto retificado
										BigDecimal valorRetificadoEsgoto = new BigDecimal(0);
																			
										// Valor Debitos Retificados
										BigDecimal valorRetificadoDebitos = new BigDecimal(0);
										
										// Valor Creditos Retificados
										BigDecimal valorRetificadoCreditos = new BigDecimal(0);
										
										// Valor Impostos Retificados
										BigDecimal valorRetificadoImpostos = new BigDecimal(0);
										
										// Consumo Agua Retificado
										Integer consumoRetificadoAgua = new Integer(0);
										
										// Consumo Esgoto Retificado
										Integer consumoRetificadoEsgoto = new Integer(0);
										
										if ( valoresContaRetificada != null && !valoresContaRetificada.isEmpty()
												&& valoresContaRetificada.size() >0 ){
										
											for (int r = 0; r < valoresContaRetificada .size(); r++) {
												
												Object objr = valoresContaRetificada .get(r);
				
												if (objr instanceof Object[]) {
													Object[] retornor = (Object[]) objr;
													
													//valor Agua retificado
													valorRetificadoAgua = (BigDecimal) retornor[0];
													if (valorRetificadoAgua == null) {
														valorRetificadoAgua = new BigDecimal(0);
													}
													
													// valor Esgoto retificado
													valorRetificadoEsgoto = (BigDecimal) retornor[1];
													if (valorRetificadoEsgoto == null) {
														valorRetificadoEsgoto = new BigDecimal(0);
													}
													
													// Valor Debitos Retificados
													valorRetificadoDebitos = (BigDecimal) retornor[2];  
													if (valorRetificadoDebitos == null) {
														valorRetificadoDebitos = new BigDecimal(0);
													}
													
													// Valor Creditos Retificados
													valorRetificadoCreditos = (BigDecimal) retornor[3];  
													if (valorRetificadoCreditos == null) {
														valorRetificadoCreditos = new BigDecimal(0);
													}
													
													// Valor Impostos Retificados
													valorRetificadoImpostos = (BigDecimal) retornor[4];  
													if (valorRetificadoImpostos == null) {
														valorRetificadoImpostos = new BigDecimal(0);
													}
													
													// Consumo Agua Retificado
													consumoRetificadoAgua = (Integer) retornor[5];  
													if (consumoRetificadoAgua == null) {
														consumoRetificadoAgua = new Integer(0);
													}
													
													// Consumo Esgoto Retificado
													consumoRetificadoEsgoto = (Integer) retornor[6];  
													if (consumoRetificadoEsgoto == null) {
														consumoRetificadoEsgoto = new Integer(0);
													}
													
												}
												
											}
										}
																							
												//Valor de Agua
												if ( valorRetificadoAgua != null && 
														( valorRetificadoAgua.compareTo(new BigDecimal(0)) > 0 ) ){
													
													//Acumula o valor cancelado de agua
													jaCadastrado.setVlCanceladoAgua(
															jaCadastrado.getVlCanceladoAgua().add(valorRetificadoAgua));
													
												} else {
													
													//Acumula o valor Incluido de Agua
													jaCadastrado.setVlIncluidoAgua(
															jaCadastrado.getVlIncluidoAgua().add( valorRetificadoAgua.negate() ));
													
												}
												
												//Valor de Esgoto
												if ( valorRetificadoEsgoto != null && 
														( valorRetificadoEsgoto.compareTo(new BigDecimal(0)) > 0 )){
													
													//Acumula o valor cancelado de esgoto
													jaCadastrado.setVlCanceladoEsgoto(
															jaCadastrado.getVlCanceladoEsgoto().add(valorRetificadoEsgoto));											
													
												} else {
													
													//Acumula o valor Incluido de Agua
													jaCadastrado.setVlIncluidoEsgoto(
															jaCadastrado.getVlIncluidoEsgoto().add( valorRetificadoEsgoto.negate() ));
													
												}
																								
												jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoCancelado) );
												jaCadastrado.setVlIncluidoOutros(jaCadastrado.getVlIncluidoOutros().add(valorDebitoIncluido));
												
												//Valor Credito
												
												jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoCancelado));
												
												jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add(valorCreditoIncluido));
												
												
												//Valor Imposto
												if ( valorRetificadoImpostos != null && 
														(valorRetificadoImpostos.compareTo(new BigDecimal(0)) > 0)){
													
													//Acumula valor cancelado de imposto
													jaCadastrado.setVlCanceladoImpostos(
															jaCadastrado.getVlCanceladoImpostos().add(valorRetificadoImpostos));
													
												} else {
													
													//Acumula valor incluido de imposto
													jaCadastrado.setVlIncluidoImpostos(
															jaCadastrado.getVlIncluidoImpostos().add(valorRetificadoImpostos.negate()));
													
												}
												
												//Consumo Agua
												if ( consumoRetificadoAgua != null && 
														consumoRetificadoAgua > 0){
													
													//Acumula o Consumo Cancelado de Agua
													jaCadastrado.setVoCanceladoAgua( 
															jaCadastrado.getVoCanceladoAgua() + consumoRetificadoAgua );
													
												} else {
													
													//Acumula o consumo incluido de agua
													jaCadastrado.setVoIncludoAgua( 
															jaCadastrado.getVoIncludoAgua() + (consumoRetificadoAgua * -1));
													
												}
												
												//Consumo Esgoto
												if ( consumoRetificadoEsgoto != null && 
														consumoRetificadoEsgoto > 0){
													
													//Acumula o consumo cancelado  de esgoto
													jaCadastrado.setVoCanceladoEsgoto( 
															jaCadastrado.getVoCanceladoEsgoto() + consumoRetificadoEsgoto );
													
												} else {
													
													//Acumula o consumo incluido de esgoto
													jaCadastrado.setVoIncluidoEsgoto( 
															jaCadastrado.getVoIncluidoEsgoto() + (consumoRetificadoEsgoto * -1));
													
												}
																						
									} else
									
									//Canceladas por Retificacao
									if (tipoConta == 4) {
										
										jaCadastrado.setQtContasRetificadas(jaCadastrado.getQtContasRetificadas()+1 );
										
										if ( jaCadastrado.getIcExistenciaContaCanceladaRetificacao() != null ){
										
											Integer icExistContaCancRetficacao = jaCadastrado.getIcExistenciaContaCanceladaRetificacao()
																				.compareTo(new Short("2"));
										
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
									
													jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorAgua));
													jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add(valorEsgoto));
													jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
													jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
													jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(valorImpostos));
													jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() +consumoAgua);
													jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() +consumoEsgoto);
											}
											
										}
									}else
									
									//Canceladas
									if(tipoConta == 3 && amContabil.equals(sistemaParametro.getAnoMesFaturamento())){
										
										jaCadastrado.setQtContasCanceladas(jaCadastrado.getQtContasCanceladas()+1);
										
										jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorAgua));
										jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add(valorEsgoto));
										jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
										jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
										jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(valorImpostos));
										jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() +consumoAgua);
										jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() +consumoEsgoto);
										
									}else
		        						
									// Incluidas
									if((tipoConta == 2 || tipoContaAnt == 2) && amContabil.equals(sistemaParametro.getAnoMesFaturamento()) ){
										
										jaCadastrado.setQtContasIncluidas(jaCadastrado.getQtContasIncluidas()+1);
										
										jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add(valorAgua));
										jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add(valorEsgoto));
										jaCadastrado.setVlIncluidoOutros(jaCadastrado.getVlIncluidoOutros().add(valorDebitoConta2ou3ou4));
										jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add(valorCreditoConta2ou3ou4));
										jaCadastrado.setVlIncluidoImpostos(jaCadastrado.getVlIncluidoImpostos().add(valorImpostos));
										jaCadastrado.setVoIncludoAgua(jaCadastrado.getVoIncludoAgua() +consumoAgua);
										jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() +consumoEsgoto);
									
									}
									
	//								 AnoMesReferencia
	
									jaCadastrado.setAnoMesReferencia(jaCadastrado.getAnoMesReferencia());
									jaCadastrado.setAnoMesReferenciaConta(jaCadastrado.getAnoMesReferenciaConta());
	
								} else {
									
			    					// Somatorios    
									
										//[SB0003]
									if (tipoConta == 1 || 
											tipoContaAnt == 1 ) {
											
										helper.setIcExistenciaContaCanceladaRetificacao(new Short("1"));
										
										// Retificada
										
//										helper.setQtContasRetificadas(helper.getQtContasRetificadas() + 1);
			
										
										List valoresContaRetificada = this.repositorioGerencialFaturamento
										.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento() , 0 );
										
//										valor Agua retificado
										BigDecimal valorRetificadoAgua = new BigDecimal(0);
										
										// valor Esgoto retificado
										BigDecimal valorRetificadoEsgoto = new BigDecimal(0);
										
										// Valor Debitos Retificados
										BigDecimal valorRetificadoDebitos = new BigDecimal(0);  
										
										// Valor Creditos Retificados
										BigDecimal valorRetificadoCreditos = new BigDecimal(0);  
										
										// Valor Impostos Retificados
										BigDecimal valorRetificadoImpostos = new BigDecimal(0);  
										
										// Consumo Agua Retificado
										Integer consumoRetificadoAgua = 0;  
										
										// Consumo Esgoto Retificado
										Integer consumoRetificadoEsgoto = 0;  
										
										if ( valoresContaRetificada != null && !valoresContaRetificada.isEmpty()
												&& valoresContaRetificada.size() >0 ){
										
											for (int r = 0; r < valoresContaRetificada .size(); r++) {
												
												Object objr = valoresContaRetificada .get(r);
				
												if (objr instanceof Object[]) {
													Object[] retornor = (Object[]) objr;
													
													//valor Agua retificado
													valorRetificadoAgua = (BigDecimal) retornor[0];
													if (valorRetificadoAgua == null) {
														valorRetificadoAgua = new BigDecimal(0);
													}
													
													// valor Esgoto retificado
													valorRetificadoEsgoto = (BigDecimal) retornor[1];
													if (valorRetificadoEsgoto == null) {
														valorRetificadoEsgoto = new BigDecimal(0);
													}
													
													// Valor Debitos Retificados
													valorRetificadoDebitos = (BigDecimal) retornor[2];  
													if (valorRetificadoDebitos == null) {
														valorRetificadoDebitos = new BigDecimal(0);
													}
													
													// Valor Creditos Retificados
													valorRetificadoCreditos = (BigDecimal) retornor[3];  
													if (valorRetificadoCreditos == null) {
														valorRetificadoCreditos = new BigDecimal(0);
													}
													
													// Valor Impostos Retificados
													valorRetificadoImpostos = (BigDecimal) retornor[4];  
													if (valorRetificadoImpostos == null) {
														valorRetificadoImpostos = new BigDecimal(0);
													}
													
													// Consumo Agua Retificado
													consumoRetificadoAgua = (Integer) retornor[5];  
													if (consumoRetificadoAgua == null) {
														consumoRetificadoAgua = new Integer(0);
													}
													
													// Consumo Esgoto Retificado
													consumoRetificadoEsgoto = (Integer) retornor[6];  
													if (consumoRetificadoEsgoto == null) {
														consumoRetificadoEsgoto = new Integer(0);
													}
													
												}
												
											}
										
										} else {
											
											helper.setIcExistenciaContaCanceladaRetificacao(new Short ("2"));
											
										}
										
										if ( helper.getIcExistenciaContaCanceladaRetificacao() != null ){
											
											Integer icExistContaCancRetficacao = helper.getIcExistenciaContaCanceladaRetificacao()
																					.compareTo(new Short("1"));
											
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
												
//												Valor de Agua
												if ( valorRetificadoAgua != null && 
														( valorRetificadoAgua.compareTo(new BigDecimal(0)) > 0 ) ){
													
													//Acumula o valor cancelado de agua
													helper.setVlCanceladoAgua(
															helper.getVlCanceladoAgua().add(valorRetificadoAgua));
																										
												} else {
													
													//Acumula o valor Incluido de Agua
													helper.setVlIncluidoAgua(
															helper.getVlIncluidoAgua().add( valorRetificadoAgua.negate() ));
													
												}
												
												//Valor de Esgoto
												if ( valorRetificadoEsgoto != null && 
														( valorRetificadoEsgoto.compareTo(new BigDecimal(0)) > 0 )){
													
													//Acumula o valor cancelado de esgoto
													helper.setVlCanceladoEsgoto(
															helper.getVlCanceladoEsgoto().add(valorRetificadoEsgoto));											
													
												} else {
													
													//Acumula o valor Incluido de Esgoto
													helper.setVlIncluidoEsgoto(
															helper.getVlIncluidoEsgoto().add( valorRetificadoEsgoto.negate() ));
													
												}
																								
												helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoCancelado) );
												
												helper.setVlIncluidoOutros(helper.getVlIncluidoOutros().add(valorDebitoIncluido));
												
												//Valor Credito
											
												helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoCancelado));
												
												helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add(valorCreditoIncluido));
												
												//Valor Imposto
												if ( valorRetificadoImpostos != null && 
														(valorRetificadoImpostos.compareTo(new BigDecimal(0)) > 0)){
													
													//Acumula valor cancelado de imposto
													helper.setVlCanceladoImpostos(
															helper.getVlCanceladoImpostos().add(valorRetificadoImpostos));
													
												} else {
													
													//Acumula valor incluido de imposto
													helper.setVlIncluidoImpostos(
															helper.getVlIncluidoImpostos().add(valorRetificadoImpostos.negate()));
													
												}
												
												//Consumo Agua
												if ( consumoRetificadoAgua != null && 
														consumoRetificadoAgua > 0){
													
													//Acumula o Consumo Cancelado de Agua
													helper.setVoCanceladoAgua( 
															helper.getVoCanceladoAgua() + consumoRetificadoAgua );
													
												} else {
													
													//Acumula o consumo incluido de agua
													helper.setVoIncludoAgua( 
															helper.getVoIncludoAgua() + (consumoRetificadoAgua * -1));
													
												}
												
												//Consumo Esgoto
												if ( consumoRetificadoEsgoto != null && 
														consumoRetificadoEsgoto > 0){
													
													//Acumula o consumo cancelado  de esgoto
													helper.setVoCanceladoEsgoto( 
															helper.getVoCanceladoEsgoto() + consumoRetificadoEsgoto );
													
												} else {
													
													//Acumula o consumo incluido de esgoto
													helper.setVoIncluidoEsgoto( 
															helper.getVoIncluidoEsgoto() + (consumoRetificadoEsgoto * -1));
													
												}
											
											}
										}
										
									}else
									
									//Canceladas por Retificacao
									if (tipoConta == 4) {
										
//										analisar
										helper.setQtContasRetificadas(helper.getQtContasRetificadas() + 1);
										
										helper.setIcExistenciaContaCanceladaRetificacao(new Short("1"));
										
										List valoresContaRetificada = this.repositorioGerencialFaturamento
										.getValorAnteriorContaRetificada(idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento(), 1  );
										
										if ( valoresContaRetificada == null || valoresContaRetificada.isEmpty() 
												|| valoresContaRetificada.size() == 0 ){
											
											helper.setIcExistenciaContaCanceladaRetificacao(new Short("2"));
											
										}
										
										if ( helper.getIcExistenciaContaCanceladaRetificacao() != null ){
																													
											
											Integer icExistContaCancRetficacao = helper.getIcExistenciaContaCanceladaRetificacao()
																					.compareTo(new Short("2"));
											
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
												
												helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorAgua));
												helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add(valorEsgoto));
												helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
												helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
												helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(valorImpostos));
												helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() +consumoAgua);
												helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() +consumoEsgoto);
																								
											}
										}
	
									}else
									
//									Canceladas
									if(tipoConta == 3 && amContabil.equals(sistemaParametro.getAnoMesFaturamento())){
										
										helper.setQtContasCanceladas( helper.getQtContasCanceladas() + 1);
										
										helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorAgua));
										helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add(valorEsgoto));
										helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
										helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
										helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(valorImpostos));
										helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() +consumoAgua);
										helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() +consumoEsgoto);
										
									}else
		        						
									// Incluidas
									if((tipoConta == 2 || tipoContaAnt == 2) && amContabil.equals(sistemaParametro.getAnoMesFaturamento()) ){
										
										helper.setQtContasIncluidas( helper.getQtContasIncluidas() + 1);
										
										helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add(valorAgua));
										helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add(valorEsgoto));
										helper.setVlIncluidoOutros(helper.getVlIncluidoOutros().add(valorDebitoConta2ou3ou4));
										helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add(valorCreditoConta2ou3ou4));
										helper.setVlIncluidoImpostos(helper.getVlIncluidoImpostos().add(valorImpostos));
										helper.setVoIncludoAgua(helper.getVoIncludoAgua() +consumoAgua);
										helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() +consumoEsgoto);
									
									}
									
									// AnoMesReferencia
	
									helper.setAnoMesReferencia(helper.getAnoMesReferencia());
									helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
									listaSimplificadaReFaturamento.add(helper); 
								
							}
						} // fim do if
							
					}//fim do for
								
					
					
					if (qtRegistros > resumoReFaturamentoContas.size()) {
						flagTerminou = true;
					} else {
						indice = indice + qtRegistros;
					}
					
				} //do while
				
				for (int i = 0; i < listaSimplificadaReFaturamento.size(); i++) {
					
					ResumoReFaturamentoNovoHelper helper = (ResumoReFaturamentoNovoHelper) listaSimplificadaReFaturamento
							.get(i);
					
					this.repositorioGerencialFaturamento.inserirResumoReFaturamentoNovo(helper);
					
				}
				
				if (listaSimplificadaReFaturamento.size() > 0 ) {
				System.out.println("final gravação dos dados");
				}
				
				
				
				getControladorBatch().encerrarUnidadeProcessamentoBatch(null,
						idUnidadeIniciada, false);
				
			} catch (Exception ex) {
				logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO REFATURAMENTO", ex);
				getControladorBatch().encerrarUnidadeProcessamentoBatch(ex, idUnidadeIniciada, true);
				throw new EJBException(ex);
			}
		
	}
	
	/**
	 * [UC0572] - Gerar Resumo ReFaturamento Novo
	 * 
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer verificarExistenciaResumoReFaturamento(Integer anoMesReferencia)
			throws ControladorException {

		try {
			return repositorioGerencialFaturamento
					.verificarExistenciaResumoReFaturamento(anoMesReferencia);

		} catch (ErroRepositorioException e) {
			throw new ControladorException("erro.sistema", e);
		}
	}
	
	/**  gerarResumoReFaturamentoGuiaPagamentoNOVO
	 * 
	 * @author Fernando Fontelles
	 * @date 01/07/2010
	 * 
	 * @param idSetor
	 * @param anoMes
	 * @param indice
	 * @param qtRegistros
	 * @throws ControladorException
	 * @throws ErroRepositorioException 
	 */
	public void gerarResumoReFaturamentoGuiaPagamentoNovo(int idSetor, int anoMes) 
	throws ControladorException {
		try {

			System.out.println("processando resumo refaturamento Guias de Pagamento para o setor:"+idSetor);	
	
			List<ResumoFaturamentoGuiaPagamentoNovoHelper> listaSimplificadaReFaturamentoGuiaPagamento = 
					new ArrayList();
			
			List resumoGuiaPagamento = this.repositorioGerencialFaturamento
					.getPesquisaGuiaPagamentoRefaturamentoNovo(idSetor, anoMes);
			
			Integer total = resumoGuiaPagamento.size();
			Integer reg = 0;

			for (int y = 0; y < resumoGuiaPagamento.size(); y++) {
				Object objGuiaPagamento = resumoGuiaPagamento.get(y);

				if (objGuiaPagamento instanceof Object[]) {
					Object[] retorno = (Object[]) objGuiaPagamento;

					Integer idImovelGuiaPagamento = (Integer) retorno[0];
					System.out.println("processando: "+reg+" de: "+total+" " +
							"Guias de Pagamento do setor = "+idSetor+"  do  Imovel = "+idImovelGuiaPagamento);
					reg++;
									
					ResumoFaturamentoGuiaPagamentoNovoHelper helperGuiaPagamento = 
						new ResumoFaturamentoGuiaPagamentoNovoHelper(
							(Integer) retorno[0],// Imovel
							(Integer) retorno[1], // Gerencia Regional
							(Integer) retorno[2], // Unidade de negocio
							(Integer) retorno[3], // Elo
							(Integer) retorno[4], // Localidade
							(Integer) retorno[5], // Id Setor Comercial
							(Integer) retorno[6], // Codigo Setor Comercial
							(Integer) retorno[7], // Perfil do Imovel
							(Integer) retorno[8], // Ligacao de Agua Situacao
							(Integer) retorno[9], // Ligacao de Esgoto Situacao
							(Integer) retorno[10], // Ligacao de Agua Perfil
							(Integer) retorno[11] // Ligacao da Esgoto Perfil
							);

					// Pesquisamos a esfera de poder do cliente
					helperGuiaPagamento
							.setIdEsfera(this.repositorioGerencialFaturamento
									.pesquisarEsferaPoderClienteResponsavelImovel(idImovelGuiaPagamento));

					// Pesquisamos o tipo de cliente responsavel 
					helperGuiaPagamento
							.setIdTipoClienteResponsavel(this.repositorioGerencialFaturamento
									.pesquisarTipoClienteClienteResponsavelImovel(idImovelGuiaPagamento));

					// Categoria
					Categoria categoriaGuiaPagamento = null;
					categoriaGuiaPagamento = this.getControladorImovel()
							.obterPrincipalCategoriaImovel(idImovelGuiaPagamento);
					if (categoriaGuiaPagamento != null) {
						helperGuiaPagamento.setIdCategoria(categoriaGuiaPagamento
								.getId());
						// Pesquisando a principal subcategoria
						ImovelSubcategoria subCategoriaGuiaPagamento = this
								.getControladorImovel()
								.obterPrincipalSubcategoria(
										categoriaGuiaPagamento.getId(), idImovelGuiaPagamento);
						if (subCategoriaGuiaPagamento != null) {
							helperGuiaPagamento
									.setIdSubCategoria(subCategoriaGuiaPagamento
											.getComp_id().getSubcategoria()
											.getId());
						}
					}
					// Verificamos se a esfera de poder foi encontrada
					if (helperGuiaPagamento.getIdEsfera().equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTempGuiaPagamento = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTempGuiaPagamento != null) {
							helperGuiaPagamento.setIdEsfera(clienteTempGuiaPagamento
									.getClienteTipo().getEsferaPoder().getId());
						}
					}

					// Verificar existencia de cliente responsavel
					if (helperGuiaPagamento.getIdTipoClienteResponsavel()
							.equals(0)) {
						Imovel imovel = new Imovel();
						imovel.setId(idImovelGuiaPagamento);
						Cliente clienteTemp = this.getControladorImovel()
								.consultarClienteUsuarioImovel(imovel);
						if (clienteTemp != null) {
							helperGuiaPagamento
									.setIdTipoClienteResponsavel(clienteTemp
											.getClienteTipo().getId());
						}
					}

					// Obter Indicador de Existência de Hidrômetro
					String indicadorHidrometroString = new Integer(
							getControladorImovel()
									.obterIndicadorExistenciaHidrometroImovel(
											idImovelGuiaPagamento)).toString();
					Short indicadorHidrometro = new Short(
							indicadorHidrometroString);
					// Caso indicador de hidrômetro esteja nulo
					// Seta 2(dois) = NÃO no indicador de
					// hidrômetro
					if (indicadorHidrometro == null) {
						indicadorHidrometro = new Short("2");
					}
					helperGuiaPagamento
							.setIndicadorHidrometro(indicadorHidrometro);

					helperGuiaPagamento.setLancamentoItemContabil((Integer) retorno[13] );
					
					helperGuiaPagamento.setIdDocumentoTipo( (Integer) retorno[14] );
					
					helperGuiaPagamento.setConsumoTarifa( (Integer) retorno[12] );
					
					BigDecimal vlGuiasCanceladas = (BigDecimal) retorno[18];
					
					helperGuiaPagamento.setIdFinanciamentoTipo( (Integer) retorno[15] );
					
					helperGuiaPagamento.setDebitoTipo( (Integer) retorno[16] );
					
					helperGuiaPagamento.setCreditoTipo( (Integer) retorno[17] );
					
					helperGuiaPagamento.setAnoMesReferencia(anoMes);
					
					// ///////////////////////////////////////////////////////////////////////////////////
					// informacos iguals
					if (listaSimplificadaReFaturamentoGuiaPagamento.contains(helperGuiaPagamento)) {
						int posicaoGuiaPagamento = listaSimplificadaReFaturamentoGuiaPagamento.indexOf(helperGuiaPagamento);
						
						ResumoFaturamentoGuiaPagamentoNovoHelper jaCadastradoGuiaPagamento = 
							(ResumoFaturamentoGuiaPagamentoNovoHelper) listaSimplificadaReFaturamentoGuiaPagamento
								.get(posicaoGuiaPagamento);

						// Guias Canceladas
						jaCadastradoGuiaPagamento.setVlGuiasCanceladas( 
								jaCadastradoGuiaPagamento.getVlGuiasCanceladas().add(vlGuiasCanceladas) );

						// Quantidade Guias Canceladas
						jaCadastradoGuiaPagamento.setQtdGuiasCanceladas( 
								jaCadastradoGuiaPagamento.getQtdGuiasCanceladas() + 1);

					} else {
						// Somatorios

						// Valor Guias Canceladas
						helperGuiaPagamento
								.setVlGuiasCanceladas(vlGuiasCanceladas);

						// Quantidade Guias Canceladas
						helperGuiaPagamento
								.setQtdGuiasCanceladas(1);

						listaSimplificadaReFaturamentoGuiaPagamento
								.add(helperGuiaPagamento);
					}
				}// if instance of de debito a cobrar
			}// for de debito a cobrar
			
			for (int i = 0; i < listaSimplificadaReFaturamentoGuiaPagamento.size(); i++) {
				ResumoFaturamentoGuiaPagamentoNovoHelper helper = (ResumoFaturamentoGuiaPagamentoNovoHelper) 
												listaSimplificadaReFaturamentoGuiaPagamento.get(i);
				
				this.repositorioGerencialFaturamento.inserirResumoReFaturamentoNovo(helper);
				
			}// do for lista simplificada
			
		}catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO REFATURAMENTO GUIA DE PAGAMENTO", ex);
			throw new EJBException(ex);
		}
	}
    
    /**
	 * Pesquisa o Resumo Faturamento para 
	 *  o Resumo com Dados para o CAS Comercial
	 *
	 * [UC1017] - Gerar Resumo com Dados para o CAS
	 *
	 * @author Daniel Alves
	 * @date 06/07/2010
	 *
	 * @return Collection<Object>
	 */
    public Collection<Object> pesquisaResumoFaturamentoResumoDadosCasComercial(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoFaturamentoResumoDadosCasComercial(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
    
    /**
	 * [UC0305] Consultar análise Faturamento
	 * 
	 * @author Hugo Amorim
	 * @date 06/08/2010
	 * 
	 */
	public Collection consultarResumoAnaliseFaturamentoDetalhe(
			InformarDadosGeracaoRelatorioConsultaHelper consultarResumoAnaliseFaturamentoDetalhe) 
            throws ControladorException{
		Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.consultarResumoAnaliseFaturamentoDetalhe(consultarResumoAnaliseFaturamentoDetalhe);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
	}
	
	/**
	 * [UC0305] Consultar análise Faturamento
	 * 		-Pesquisa para geração relatorio.
	 * @author Hugo Amorim
	 * @date 06/08/2010
	 * 
	 */
	public List consultarResumoAnaliseFaturamentoRelatorio(
			InformarDadosGeracaoRelatorioConsultaHelper 
				informarDadosGeracaoRelatorioConsultaHelper)
					throws ControladorException{
		
		List retorno = null;
		
		try {

			retorno = repositorioGerencialFaturamento
					.consultarResumoAnaliseFaturamentoRelatorio(informarDadosGeracaoRelatorioConsultaHelper);

			// [FS0007] Nenhum registro encontrado
			if (retorno == null || retorno.isEmpty()) {
				throw new ControladorException(
						"atencao.pesquisa.nenhumresultado");
			}

		} catch (ErroRepositorioException ex) {
			ex.printStackTrace();
			throw new ControladorException("erro.sistema", ex);
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
    public Collection<Object> pesquisaResumoInstalacaoHidrometroResumoDadosCas(FiltrarResumoDadosCasHelper filtro)throws ControladorException {
    	Collection retorno = null;
    	try {
			retorno = this.repositorioGerencialFaturamento.pesquisaResumoInstalacaoHidrometroResumoDadosCas(filtro);
		} catch (ErroRepositorioException e) {
			throw new ControladorException( e.getMessage(), e );
		}
    	return retorno;
    }
	
    /**
     * [UC057] - Gerar Resumo do Faturamento
     *
     * [SB0015] - Gerar Resumo Credito A Realizar
     *
     * @author Ivan Sergio
     * @date 18/01/2011
     *
     * @param idSetor
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    private void gerarResumoCreditoARealizar(int idSetor, int idFuncionalidadeIniciada) throws ControladorException {
    	
    	try{
            // Selecionamos os Creditos a Realizar
            Collection<Object[]> colCreditoARealizar = this.repositorioGerencialFaturamento
                .pesquisarCreditoARealizar( idSetor );        
            
            Iterator iteCreditoARealizar = colCreditoARealizar.iterator();
            
            List<ResumoFaturamentoHelper> listaResumo = new ArrayList<ResumoFaturamentoHelper>();
    
            // Selecionamos os dados
            int i = 1;
            
            while (iteCreditoARealizar.hasNext()) {
           	 
            	if (i % 100 == 0 || i == 1) {             
            		System.out.println("AGRUPANDO CREDITO A REALIZAR " + i + " DE " + colCreditoARealizar.size());
            	}
            	++i;
                
                // Pegamos a linha atual
                Object[] linha = (Object[]) iteCreditoARealizar.next();
                
                // SB0016 – Preparar Dados do Resumo para Crédito a Realizar
                // Criamos um helper para Credito A Realizar
                Collection colHelperCreditoARealizar = montarResumoCreditoARealizar( linha );
                for (Iterator iter = colHelperCreditoARealizar.iterator(); iter
						.hasNext();) {
                	ResumoFaturamentoHelper helperCreditoARealizar = (ResumoFaturamentoHelper) iter.next();
	                // Verificamos se ja existe um helper com as quebras
	                // inserido na lista de resumo. Se sim, apenas somamos
	                // os valores, senão, colocamos um helper novo na lista
	                
	                if ( listaResumo.contains( helperCreditoARealizar ) ){
	                    int posicao = listaResumo.indexOf(helperCreditoARealizar);
	                    ResumoFaturamentoHelper jaCadastrado = 
	                        (ResumoFaturamentoHelper) listaResumo.get(posicao);
	                    
	                    somarValoresParaResumoFaturamentoCreditoARealizar( jaCadastrado, helperCreditoARealizar );                    
	                } else {
	                    listaResumo.add( helperCreditoARealizar );
	                }
                }
            }
            
            // Inserimos todos os helpers de resumo
            Iterator iteResumo = listaResumo.iterator();
            i=1;
            while ( iteResumo.hasNext() ){
            	ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
                
                if ( i % 100 == 0 || i == 1 ) System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
                ++i;
                
                repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
            }             
        } catch ( ErroRepositorioException e ){
            throw new ControladorException( e.getMessage(), e );
        }
    }
	
    /**
     * 
     * [UC0571] - Gerar Resumo do Faturamento
     * SB0016 – Preparar Dados do Resumo para Crédito a Realizar
     *
     * @author Ivan Sergio
     * @date 18/01/2011
     *
     * @param linha - Linha do select que contem os dados da conta
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    private Collection montarResumoCreditoARealizar(Object[] linha) throws ControladorException, ErroRepositorioException{
        
    	Collection colResumoFaturamentoHelper = new ArrayList();
    	
    	Collection colCreditoARealizarCategoria = repositorioFaturamento.pesquisarCreditoARealizarCategoriaResumo( (Integer) linha[28]);
    	
    	Iterator iterCreditoARealizarCategoria = colCreditoARealizarCategoria.iterator();
    	
    	int tamanhoColecao = colCreditoARealizarCategoria.size();
    	
    	int contador = 1;
    	
    	Integer qtEconomias = repositorioFaturamento.pesquisarQuantidadeEconomiasCreditoARealizar((Integer) linha[28]);
    	
    	BigDecimal valorResidual = (BigDecimal) linha[27];
    	
    	BigDecimal somaResidual = BigDecimal.ZERO;

    	while (iterCreditoARealizarCategoria.hasNext()) {
    		
			CreditoARealizarCategoria creditoARealizarCategoria = (CreditoARealizarCategoria) iterCreditoARealizarCategoria.next();
			System.out.println("Crar_id: " + creditoARealizarCategoria.getCreditoARealizar().getId());
			System.out.println("  Catg_id: " + creditoARealizarCategoria.getCategoria().getId());
			
			BigDecimal valorResidualCategoria = (valorResidual.divide(new BigDecimal(qtEconomias),2, BigDecimal.ROUND_HALF_UP))
					.multiply(new BigDecimal(creditoARealizarCategoria.getQuantidadeEconomia()));
			
			ResumoFaturamentoHelper helperCreditoARealizar = new ResumoFaturamentoHelper();
			
			// Informamos que esse helper é do tipo AguaEsgoto
	        helperCreditoARealizar.setTipoResumo(ResumoFaturamentoHelper.RESUMO_CREDITO_A_REALIZAR);
	        
	        // Setamos as quebras para o helper
	        helperCreditoARealizar.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
	        helperCreditoARealizar.setIdGerenciaRegional( (Integer) linha[0] );
	        helperCreditoARealizar.setIdUnidadeNegocio( (Integer) linha[1] );
	        helperCreditoARealizar.setCdElo( (Integer) linha[2] );
	        helperCreditoARealizar.setIdLocalidade( (Integer) linha[3] );
	        helperCreditoARealizar.setIdSetorComercial( (Integer) linha[4] );
	        helperCreditoARealizar.setCdSetorComercial( (Integer) linha[5] );
	        helperCreditoARealizar.setIdRota( (Integer) linha[6] );
	        helperCreditoARealizar.setCdRota( (Short) linha[7] );
	        helperCreditoARealizar.setIdQuadra( (Integer) linha[8] );
	        helperCreditoARealizar.setNmQuadra( (Integer) linha[9] );
	        helperCreditoARealizar.setIdPerfilImovel( (Integer) linha[10] );
	        helperCreditoARealizar.setSituacaoLigacaoAgua( (Integer) linha[11] );
	        helperCreditoARealizar.setSituacaoLigacaoEsgoto( (Integer) linha[12] );
	        
	        // [UC0306] - Obter Principal Categoria do Imovel
	        // pesquisando a categoria
	        Imovel imovel = new Imovel( (Integer) linha[21] );
	        Categoria categoria = creditoARealizarCategoria.getCategoria();
	        helperCreditoARealizar.setIdCategoria( categoria.getId() );
	        // ---------------------------------------------       
	        
	        // Pesquisamos a subcategoria
	        ImovelSubcategoria subcategoria = null;
	        if (categoria != null) {
	            // Pesquisando a principal subcategoria
	            subcategoria = this
	                    .getControladorImovel()
	                    .obterPrincipalSubcategoria(categoria.getId(),
	                            imovel.getId());
	
	            if (subcategoria != null) {
	            	helperCreditoARealizar.setIdSubcategoria(
	                        subcategoria.getComp_id().getSubcategoria().getId() );
	            } else {
	            	helperCreditoARealizar.setIdSubcategoria( 0 );
	            }
	        }
	        // ---------------------------------------------
	        
	        // Esfera de Poder do Cliente Conta
	        // SB0010 - Obter dados do cliente da conta
	        // Pesquisamos a esfera de poder do cliente responsavel
	        helperCreditoARealizar.setIdEsferaPoder(
	                this.repositorioGerencialCadastro
	                        .pesquisarEsferaPoderClienteResponsavelImovel( imovel.getId() ) );
	        // Pesquisamos o tipo de cliente responsavel do imovel
	        helperCreditoARealizar.setIdTipoCliente(
	                this.repositorioGerencialCadastro
	                        .pesquisarTipoClienteClienteResponsavelImovel(imovel.getId()));
	        
	        // Verificamos se a esfera de poder foi encontrada
	        // para o cliente tipo responsavel, caso nao tenh
	        // pesquisamos pelo cliente usuario
	        if (helperCreditoARealizar.getIdEsferaPoder().equals(0)) {
	            Cliente clienteTemp = 
	                this.getControladorImovel()
	                    .consultarClienteUsuarioImovel(imovel);
	            
	            if (clienteTemp != null) {
	            	helperCreditoARealizar.setIdEsferaPoder(
	                        clienteTemp.getClienteTipo().getEsferaPoder().getId() );
	            }
	        }
	        
	        // Verificamos se o cliente tipo responsavel foi encontrado, caso nao tenha sido
	        // pesquisamos pelo cliente usuario
	        if (helperCreditoARealizar.getIdTipoCliente().equals(0)) {
	            
	            Cliente clienteTemp = this.getControladorImovel()
	                    .consultarClienteUsuarioImovel(imovel);
	            
	            if (clienteTemp != null) {
	            	helperCreditoARealizar.setIdTipoCliente(clienteTemp.getClienteTipo().getId() );
	            }
	        }
	        // ------------------------------------------------------
	        
	        helperCreditoARealizar.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
	        helperCreditoARealizar.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
	        helperCreditoARealizar.setIdTarifaConsumo( (Integer) linha [15] );
	        helperCreditoARealizar.setIdGrupoFaturamento( (Integer) linha [16] );
	        helperCreditoARealizar.setIdEmpresa( (Integer) linha [17] );
	        
	        helperCreditoARealizar.setIdOrigemCredito((Integer) linha[18]);
	        
	        // Verificamos a existencia de hidrometro
	        helperCreditoARealizar.setIndHidrometro( 
	                this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );
	        
	        helperCreditoARealizar.setIdItemLancamentoContabil( (Integer) linha [19] );
	        helperCreditoARealizar.setIdTipoDocumento( 10 );
	        
	        helperCreditoARealizar.setIdTipoFinanciamento(null);
	        helperCreditoARealizar.setIdTipoDebito(null);
	        
	        helperCreditoARealizar.setIdTipoCredito((Integer) linha[20]);
	        helperCreditoARealizar.setIdTipoImposto(null);
	        
	        //****************************************************************************************************************
	        BigDecimal valorCredito = creditoARealizarCategoria.getValorCategoria();
	        Short numeroPrestacaoCredito = (Short) linha[25]; 
	        Short numeroPrestacaoRealizada = (Short) linha[26];
	        BigDecimal valorResidualMesAnterior = BigDecimal.ZERO;
	        
	        if (contador != qtEconomias){
	        	valorResidualMesAnterior = valorResidualCategoria;
	        	somaResidual = somaResidual.add(valorResidualCategoria);
	        	contador = contador + 1;
	        } else {
	        	
	        	valorResidualMesAnterior = valorResidual.subtract(somaResidual);
	        }
	        
	        Integer debitoCreditoSituacaoAtual = ConstantesSistema.NUMERO_NAO_INFORMADO;
	        Integer debitoCreditoSituacaoAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
	        
	        if(linha[23] != null){
	        	debitoCreditoSituacaoAtual = (Integer) linha[23];
	        }
	        
	        if(linha[24] != null){
	        	debitoCreditoSituacaoAnterior = (Integer) linha[24];
	        }
	        
	        // Valor Creditos A Realizar Cobrancas Indevidas Cancelados
	        if ( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.CANCELADA.intValue() ) &&
	        		(helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_TARIFA_AGUA) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO) ) ) {
	        	
	            BigDecimal valor = 
	                valorCredito.
	                	subtract(valorCredito.divide(new BigDecimal(numeroPrestacaoCredito), 2, BigDecimal.ROUND_HALF_UP).
	                    multiply(new BigDecimal(numeroPrestacaoRealizada))).
	                    add(valorResidualMesAnterior);
	            
	            helperCreditoARealizar.setValorCreditosARealizarCobrancasIndevidasCancelados(valor);
	        }
	        
	        // Valor Descontos Incondicionais Cancelados
	        if ( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.CANCELADA.intValue()) &&
	        		(helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DESCONTOS_INCONDICIONAIS) ) ) {
	        	
	            BigDecimal valor = 
	                valorCredito.
	                	subtract(valorCredito.divide(new BigDecimal(numeroPrestacaoCredito), 2, BigDecimal.ROUND_HALF_UP).
	                	multiply(new BigDecimal(numeroPrestacaoRealizada))).
	                	add(valorResidualMesAnterior);
	            
	            helperCreditoARealizar.setValorDescontosIncondicionaisCancelados(valor);
	        }
	        
	        // Valor Creditos A Realizar Cobrancas Indevidas Incluidos
	        if ( ( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.NORMAL.intValue()) || 
	        		(debitoCreditoSituacaoAnterior.intValue() == DebitoCreditoSituacao.NORMAL.intValue()) ) &&
	        		(helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_TARIFA_AGUA) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_TARIFA_ESGOTO) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE) ||
	        		 helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DEVOLUCAO_JUROS_PARCELAMENTO) ) ) {
	        	
	        	/*BigDecimal valor = 
	                valorCredito.
	                	subtract(valorCredito.divide(new BigDecimal(numeroPrestacaoCredito), 2, BigDecimal.ROUND_HALF_UP).
	                	multiply(new BigDecimal(numeroPrestacaoRealizada))).
	                	add(valorResidualMesAnterior);*/
	        	
	        	BigDecimal valor =  valorCredito;
	            
	            helperCreditoARealizar.setValorCreditosARealizarCobrancasIndevidasIncluidos(valor);
	        }
	        
	        // Valor Descontos Incondicionais Incluidos
	        if ( ( (debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.NORMAL.intValue()) || 
	        		(debitoCreditoSituacaoAnterior.intValue() == DebitoCreditoSituacao.NORMAL.intValue()) ) &&
	        		(helperCreditoARealizar.getIdOrigemCredito().equals(CreditoOrigem.DESCONTOS_INCONDICIONAIS) ) ) {
	        	
	           /* BigDecimal valor = 
	                valorCredito.
	                	subtract(valorCredito.divide(new BigDecimal(numeroPrestacaoCredito), 2, BigDecimal.ROUND_HALF_UP).
	                	multiply(new BigDecimal(numeroPrestacaoRealizada))).
	                	add(valorResidualMesAnterior);*/
	        	
	        	BigDecimal valor =  valorCredito;
	            
	            helperCreditoARealizar.setValorDescontosIncondicionaisIncluidos(valor);
	        }
	        
	        colResumoFaturamentoHelper.add(helperCreditoARealizar);
    	}
        
        return colResumoFaturamentoHelper;
    }
    
	/**
	 * 
	 * UC0571 - Gerar Resumo Faturamento
	 * [SB0016] – Preparar Dados do Resumo para Créditos a Realizar
	 *
	 * @author Ivan Sergio
	 * @date 19/01/2011
	 *
	 * @param jaCadastrado
	 * @param helperFinanciamento
	 * @return
	 */
    private void somarValoresParaResumoFaturamentoCreditoARealizar(
    		ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperFinanciamento){
    	
    	jaCadastrado.setValorCreditosARealizarCobrancasIndevidasCancelados(
	            jaCadastrado.getValorCreditosARealizarCobrancasIndevidasCancelados().add(
	            		helperFinanciamento.getValorCreditosARealizarCobrancasIndevidasCancelados()));
	    
	    jaCadastrado.setValorDescontosIncondicionaisCancelados(
	            jaCadastrado.getValorDescontosIncondicionaisCancelados().add(
	            		helperFinanciamento.getValorDescontosIncondicionaisCancelados()));
	    
	   	jaCadastrado.setValorCreditosARealizarCobrancasIndevidasIncluidos(
	   			jaCadastrado.getValorCreditosARealizarCobrancasIndevidasIncluidos().add(
	   					helperFinanciamento.getValorCreditosARealizarCobrancasIndevidasIncluidos()));
	   	
	   	jaCadastrado.setValorDescontosIncondicionaisIncluidos(
	   			jaCadastrado.getValorDescontosIncondicionaisIncluidos().add(
	   					helperFinanciamento.getValorDescontosIncondicionaisIncluidos()));
	}
    
    /**
     * [UC057] - Gerar Resumo do Faturamento
     *
     * [SB0017] -  Gerar Resumo Guias de Devolução.
     *
     * @author Ivan Sergio
     * @date 19/01/2011
     *
     * @param idSetor
     * @param idFuncionalidadeIniciada
     * @throws ControladorException
     */
    private void gerarResumoGuiasDevolucao(int idSetor, int idFuncionalidadeIniciada) throws ControladorException {
    	
    	try{
            // Selecionamos as Guias de Devolucao
            Collection<Object[]> colGuiasDevolucao = this.repositorioGerencialFaturamento
                .pesquisarGuiasDevolucao( idSetor );        
            
            Iterator iteGuiasDevolucao = colGuiasDevolucao.iterator();
            
            List<ResumoFaturamentoHelper> listaResumo = new ArrayList<ResumoFaturamentoHelper>();
    
            // Selecionamos os dados
            int i = 1;
            
            while (iteGuiasDevolucao.hasNext()) {
           	 
            	if (i % 100 == 0 || i == 1) {             
            		System.out.println("AGRUPANDO GUIAS DE DEVOLUCAO " + i + " DE " + colGuiasDevolucao.size());
            	}
            	++i;
                
                // Pegamos a linha atual
                Object[] linha = (Object[]) iteGuiasDevolucao.next();
                
                // SB0018 – Preparar Dados do Resumo para Guias de Devolucao
                // Criamos um helper
                ResumoFaturamentoHelper helperGuiasDevolucao = montarResumoGuiasDevolucao( linha );
                
                // Verificamos se ja existe um helper com as quebras
                // inserido na lista de resumo. Se sim, apenas somamos
                // os valores, senão, colocamos um helper novo na lista
                if ( listaResumo.contains( helperGuiasDevolucao ) ){
                    int posicao = listaResumo.indexOf(helperGuiasDevolucao);
                    ResumoFaturamentoHelper jaCadastrado = 
                        (ResumoFaturamentoHelper) listaResumo.get(posicao);
                    
                    somarValoresParaResumoFaturamentoGuiasDevolucao( jaCadastrado, helperGuiasDevolucao );                    
                } else {
                    listaResumo.add( helperGuiasDevolucao );
                }
                // --------------------------------------------
            }
            
            // Inserimos todos os helpers de resumo
            Iterator iteResumo = listaResumo.iterator();
            i=1;
            while ( iteResumo.hasNext() ){
            	ResumoFaturamentoHelper helper = ( ResumoFaturamentoHelper ) iteResumo.next();
                
                if ( i % 100 == 0 || i == 1 ) System.out.println( "INSERINDO RESUMO " + i + " DE " + listaResumo.size() );
                ++i;
                
                repositorioGerencialFaturamento.inserirResumoFaturamento( helper );
            }             
        } catch ( ErroRepositorioException e ){
            throw new ControladorException( e.getMessage(), e );
        }
    }
    
    /**
     * 
     * [UC0571] - Gerar Resumo do Faturamento
     * SB0018 - Preparar Dados do Resumo para Guias de Devolução
     *
     * @author Ivan Sergio
     * @date 19/01/2011
     *
     * @param linha - Linha do select que contem os dados da guia
     * @return
     * @throws ControladorException
     * @throws ErroRepositorioException
     */
    private ResumoFaturamentoHelper montarResumoGuiasDevolucao( Object[] linha ) throws ControladorException, ErroRepositorioException{
        
        ResumoFaturamentoHelper helperGuia = new ResumoFaturamentoHelper();
        
        // Informamos que esse helper é do tipo AguaEsgoto
        helperGuia.setTipoResumo( ResumoFaturamentoHelper.RESUMO_GUIAS_DEVOLUCAO );
        
        // Setamos as quebras para o helper
        helperGuia.setAnoMesFaturamento( this.getControladorUtil().pesquisarParametrosDoSistema().getAnoMesFaturamento() );
        helperGuia.setIdGerenciaRegional( (Integer) linha[0] );
        helperGuia.setIdUnidadeNegocio( (Integer) linha[1] );
        helperGuia.setCdElo( (Integer) linha[2] );
        helperGuia.setIdLocalidade( (Integer) linha[3] );
        
        // Verificamos se o imovel foi informado na guia de pagamento
        // caso positivo, trazemos os dados da guia, caso negativo
        // pegamos os dados do imovel
        if ( (Integer) linha[18] != null ){
        	helperGuia.setIdSetorComercial( (Integer) linha[4] );
            helperGuia.setCdSetorComercial( (Integer) linha[5] );
            helperGuia.setIdRota( (Integer) linha[6] );
            helperGuia.setCdRota( (Short) linha[7] );
            helperGuia.setIdQuadra( (Integer) linha[8] );
            helperGuia.setNmQuadra( (Integer) linha[9] );
            helperGuia.setIdGrupoFaturamento( (Integer) linha[21] );
            helperGuia.setIdEmpresa( (Integer) linha[22] );
            
            // [UC0306] - Obter Principal Categoria do Imovel
            // pesquisando a categoria
            Imovel imovel = new Imovel( (Integer) linha[18] );
            Categoria categoria = 
                this.getControladorImovel().obterPrincipalCategoriaImovel( imovel.getId() );
            helperGuia.setIdCategoria( categoria.getId() );
            // ---------------------------------------------       
            
            // Pesquisamos a subcategoria
            ImovelSubcategoria subcategoria = null;
            if (categoria != null) {
                // Pesquisando a principal subcategoria
                subcategoria = this
                        .getControladorImovel()
                        .obterPrincipalSubcategoria(categoria.getId(),
                                imovel.getId());

                if (subcategoria != null) {
              	  helperGuia.setIdSubcategoria(
                            subcategoria.getComp_id().getSubcategoria().getId() );
                }
            }
            // ---------------------------------------------
            
            // Verificamos a existencia de hidrometro
            helperGuia.setIndHidrometro( 
                    this.repositorioGerencialCadastro.verificarExistenciaHidrometro( imovel.getId() ) );          
        } else {
            // Como a guia nao possue imovel atribuimos o primeiro setor comercial 
            // da localidade da guia
            FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
            filtroSetorComercial.adicionarParametro( new ParametroSimples( FiltroSetorComercial.ID_LOCALIDADE, (Integer) linha[3] ) );
            filtroSetorComercial.setCampoOrderBy( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL );
            SetorComercial setorComercial = 
                (SetorComercial) Fachada.getInstancia().pesquisar( filtroSetorComercial, SetorComercial.class.getName() ).iterator().next();
            
            // Setamos o setor comercial no helper
            helperGuia.setIdSetorComercial( setorComercial.getId() );
            helperGuia.setCdSetorComercial( setorComercial.getCodigo() );
            
            // Com o setor comercial selecionado, selecionamos a primeira quadra
            FiltroQuadra filtroQuadra = new FiltroQuadra();
            filtroQuadra.adicionarParametro( new ParametroSimples( FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId() ) );
            filtroQuadra.adicionarCaminhoParaCarregamentoEntidade( "rota.faturamentoGrupo" );
            filtroQuadra.setCampoOrderBy( FiltroQuadra.ID );
            Quadra quadra = 
                (Quadra) Fachada.getInstancia().pesquisar( filtroQuadra, Quadra.class.getName() ).iterator().next();    
            
            // Setamos a rota no helper
            helperGuia.setIdRota( quadra.getRota().getId() );
            helperGuia.setCdRota( quadra.getRota().getCodigo() );
            helperGuia.setIdGrupoFaturamento( quadra.getRota().getFaturamentoGrupo().getId() );
            // Setamos a quadra no helper
            helperGuia.setIdQuadra( quadra.getId() );
            helperGuia.setNmQuadra( quadra.getNumeroQuadra() );          
            helperGuia.setIdEmpresa( quadra.getRota().getEmpresa().getId() );
            
            helperGuia.setIdCategoria( 1 );
            helperGuia.setIdSubcategoria( 10 );
            helperGuia.setIndHidrometro( 2 );
        }
        
        // Verificamos se existe cliente responsavel para o imovel da guia 
        if ( linha[20] != null ){
            helperGuia.setIdTipoCliente( (Integer) linha[20] );
            helperGuia.setIdEsferaPoder( (Integer) linha[19] );              
        } else {
            // Esfera de Poder do Cliente da Guia
            // SB0011 - Obter dados do cliente da Guia
            // Pesquisamos a esfera de poder do cliente responsavel
            helperGuia.setIdEsferaPoder(
                    this.repositorioGerencialCadastro
                            .pesquisarEsferaPoderClienteResponsavelImovel( (Integer) linha[18] ) );
            // Pesquisamos o tipo de cliente responsavel do imovel
            helperGuia.setIdTipoCliente(
                    this.repositorioGerencialCadastro
                            .pesquisarTipoClienteClienteResponsavelImovel( (Integer) linha[18]) );              
        }      

        helperGuia.setIdPerfilImovel( (Integer) linha[10] );
        helperGuia.setSituacaoLigacaoAgua( (Integer) linha[11] );
        helperGuia.setSituacaoLigacaoEsgoto( (Integer) linha[12] );     
        helperGuia.setIdPerfilLigacaoAgua( (Integer) ( linha [13] == null ? 0 : linha [13] ) );
        helperGuia.setIdPerfilLigacaoEsgoto( (Integer) ( linha [14] == null ? 0 : linha [14] ) );
        helperGuia.setIdTarifaConsumo( (Integer) linha [15] );
        
        helperGuia.setIdItemLancamentoContabil( (Integer) linha [16] );
        helperGuia.setIdTipoDocumento( 8 );
        
        helperGuia.setIdTipoFinanciamento( null );
        helperGuia.setIdTipoDebito( (Integer) linha [17] );
        
        Integer debitoCreditoSituacaoAtual = ConstantesSistema.NUMERO_NAO_INFORMADO;
        Integer debitoCreditoSituacaoAnterior = ConstantesSistema.NUMERO_NAO_INFORMADO;
        
        if(linha[24] != null){
        	debitoCreditoSituacaoAtual = (Integer) linha[24];
        }
        
        if(linha[25] != null){
        	debitoCreditoSituacaoAnterior  = (Integer) linha[25];
        }
        
        // Valor Guia Devolucoes Cobrados Indevidamente Cancelados
        if ( debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.CANCELADA.intValue() ) {

        	helperGuia.setValorGuiaDevolucaoCobradosIndevidamenteCancelados( (BigDecimal) linha[23] );
        }
        
        // Valor Guia Devolucoes Cobrados Indevidamente Incluido
        if ( ( debitoCreditoSituacaoAtual.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ) || 
        		( debitoCreditoSituacaoAnterior.intValue() == DebitoCreditoSituacao.NORMAL.intValue() ) ) {
        	
        	helperGuia.setValorGuiaDevolucaoCobradosIndevidamenteIncluidos( (BigDecimal) linha[23] );
        }
        
        return helperGuia;
    }
    
    /**
	 * 
	 * UC0571 - Gerar Resumo Faturamento
	 * [SB0018] – Preparar Dados do Resumo para Guias de Devolução
	 *
	 * @author Ivan Sergio
	 * @date 20/01/2011
	 *
	 * @param jaCadastrado
	 * @param helperFinanciamento
	 * @return
	 */
    private void somarValoresParaResumoFaturamentoGuiasDevolucao(
    		ResumoFaturamentoHelper jaCadastrado, ResumoFaturamentoHelper helperGuiasDevolucao){
    	
    	jaCadastrado.setValorGuiaDevolucaoCobradosIndevidamenteCancelados(
	            jaCadastrado.getValorGuiaDevolucaoCobradosIndevidamenteCancelados().add(
	            		helperGuiasDevolucao.getValorGuiaDevolucaoCobradosIndevidamenteCancelados()));
	    
	    jaCadastrado.setValorGuiaDevolucaoCobradosIndevidamenteIncluidos(
	            jaCadastrado.getValorGuiaDevolucaoCobradosIndevidamenteIncluidos().add(
	            		helperGuiasDevolucao.getValorGuiaDevolucaoCobradosIndevidamenteIncluidos()));
	}
    
    
    /**
	 * Método que gera o resumo do ReFaturamento Novo
	 * 
	 * [UC0572] - Gerar Resumo do ReFaturamento Novo
	 * 
	 * @author Fernando Fontelles
	 * @param idSetor 
	 * @param anoMes 
	 * @date 29/06/2010
	 * 
	 */
	public void gerarResumoReFaturamentoNovo(int idSetor,
			int idFuncionalidadeIniciada, int anoMes) throws ControladorException {

		int idUnidadeIniciada = 0;

		// -------------------------
		//
		// Registrar o início do processamento da Unidade de
		// Processamento
		// do Batch
		//
		// -------------------------
		
		idUnidadeIniciada = getControladorBatch()
				.iniciarUnidadeProcessamentoBatch(idFuncionalidadeIniciada,
						UnidadeProcessamento.SETOR_COMERCIAL, idSetor);
		
			try {
				// Listas de Controle
				List<ResumoReFaturamentoNovoHelper> listaSimplificadaReFaturamento = new ArrayList<ResumoReFaturamentoNovoHelper>();
				
				SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();

				// Indices da paginacao
				int indice = 0;
				int qtRegistros = 500;
				// flag da paginacao 
				boolean flagTerminou = false;
				// contador de paginacao(informativo no debug)
				int count = 0;
				// inicio do processamento
				BigDecimal valorCanceladoServico4menos1 = BigDecimal.ZERO;
				BigDecimal valorCanceladoServico2ou3 = BigDecimal.ZERO;
				
				//	[SB0002 ? Gerar Resumo Guias de Pagamento].

				this.gerarResumoReFaturamentoGuiaPagamentoNovo(idSetor, anoMes);
				
				while(!flagTerminou) {
					
					count++;
					
					//[SB0001 ? Gerar Resumo Contas]

					List resumoReFaturamentoContas = this.repositorioGerencialFaturamento.
					getResumoRefaturamentoContas(idSetor , anoMes, indice, qtRegistros);
					
					System.out.println("processando resumo refaturamento para o setor:"+idSetor); 
					
					for (int i = 0; i < resumoReFaturamentoContas .size(); i++) {
						
						int qtdreg = i + 1;
						
						Object obj = resumoReFaturamentoContas .get(i);
						
						System.out.println("registro: "+qtdreg+" / "+resumoReFaturamentoContas .size());
						
						if (obj instanceof Object[]) {
							Object[] retorno = (Object[]) obj;
							//Collection colContaCategoria = repositorioFaturamento.pesquisarContaCategoria((Integer)retorno[0]);
							
							Collection colContaCategoria = this.pesquisarContaCategoria((Integer)retorno[0]);
							
							
							for (Iterator iter = colContaCategoria.iterator(); iter.hasNext();) {
								
								// Montamos um objeto de resumo, com as informacoes do
								// retorno
								ResumoReFaturamentoNovoHelper helper = new ResumoReFaturamentoNovoHelper(
										(Integer) retorno[2],  // Gerencia Regional
										(Integer) retorno[3],  // Unidade de negocio
										(Integer) retorno[4],  // Elo 
										(Integer) retorno[5],  // Localidade
										(Integer) retorno[6],  // Id Setor Comercial
										(Integer) retorno[7],  // Codigo do Setor Comercial
										(Integer) retorno[8], // Perfil do imovel
										(Integer) retorno[9], // Situacao da ligacao da agua
										(Integer) retorno[10], // Situacao da ligacao do esgoto
										(Integer) retorno[11], // Perfil da ligacao do agua
										(Integer) retorno[12]);// Perfil da ligacao do esgoto
								
								Integer icExistContaCancRetficacao = null;
								
								ContaCategoria contaCategoria = (ContaCategoria) iter.next();
								
								Integer tipoConta = (Integer) retorno[19];
								
								Integer tipoContaAnt = (Integer) retorno[24];
								
								Integer amContabil = (Integer) retorno[20];
								
								
								if (tipoContaAnt == null) {
									tipoContaAnt = 9999;
								}
								
								
								// consumoAgua
								Integer consumoAgua = contaCategoria.getConsumoAgua();  
								if (consumoAgua == null) {
									consumoAgua = 0;
								}
			
								// consumoEsgoto
								Integer consumoEsgoto = contaCategoria.getConsumoEsgoto();
								if (consumoEsgoto == null) {
									consumoEsgoto = 0;
								}
			
								// valorAgua
								BigDecimal valorAgua = contaCategoria.getValorAgua();
								if (valorAgua == null) {
									valorAgua = new BigDecimal(0);
								}
								
								// valorEsgoto
								BigDecimal valorEsgoto = contaCategoria.getValorEsgoto();
								if (valorEsgoto == null) {
									valorEsgoto = new BigDecimal(0);
								}
								
								Integer anoMesRefConta = (Integer) retorno[18];
								Integer anoMesRef = sistemaParametro.getAnoMesFaturamento();
	
								// Impostos
								BigDecimal valorImpostos = (BigDecimal) retorno[21];  
								if (valorImpostos == null) {
									valorImpostos = new BigDecimal(0);
								}
								
								Integer consumoTarifa = (Integer) retorno[13];
								helper.setConsumoTarifa(consumoTarifa);
								helper.setDocumentoTipo((Integer) retorno[25]);
								
								// AnoMesReferencia
								helper.setAnoMesReferencia(anoMesRef);
								helper.setAnoMesReferenciaConta(anoMesRefConta);
								
								// Pesquisamos a esfera de poder do cliente responsavel
								helper.setIdEsfera( this.repositorioGerencialFaturamento.
										pesquisarEsferaPoderClienteResponsavelImovel( (Integer) retorno[1]/*imovel*/ ) );
								
								// Pesquisamos o tipo de cliente responsavel do imovel
								helper.setIdTipoClienteResponsavel( this.repositorioGerencialFaturamento.
										pesquisarTipoClienteClienteResponsavelImovel( (Integer) retorno[1] ) );
								
								// pesquisando a categoria
								// [UC0306] - Obtter principal categoria do imóvel
								Integer idImovel = ( Integer )retorno[1]; // Codigo do imovel que esta sendo processado
								
								if (idImovel.equals(4359)){
									System.out.println("PAROU");
								}
								
								helper.setIdCategoria(contaCategoria.getComp_id().getCategoria().getId());
								
								if ( contaCategoria.getComp_id().getSubcategoria() != null ){
									helper.setIdSubCategoria(contaCategoria.getComp_id().getSubcategoria().getId());
								} else {
									helper.setIdSubCategoria(0);
								}
								
								// Verificamos se a esfera de poder foi encontrada
								// [FS0002] Verificar existencia de cliente responsavel
								if ( helper.getIdEsfera().equals( 0 ) ){
									Imovel imovel = new Imovel();
									imovel.setId(idImovel);
									Cliente clienteTemp = this.getControladorImovel().
												consultarClienteUsuarioImovel( imovel );
									if ( clienteTemp != null ){
									  helper.setIdEsfera( clienteTemp.getClienteTipo().getEsferaPoder().getId() );
									}
								}	
								
								if ( helper.getIdTipoClienteResponsavel().equals( 0 ) ){
									Imovel imovel = new Imovel();
									imovel.setId(idImovel);
									Cliente clienteTemp = this.getControladorImovel().consultarClienteUsuarioImovel( imovel );
									if ( clienteTemp != null ){
									  helper.setIdTipoClienteResponsavel( clienteTemp.getClienteTipo().getId() );
									}
								}
								
								//Verifica a existencia do Hidrometro
								Boolean existeHidrometro = Fachada.getInstancia().
											verificarExistenciaHidrometroEmLigacaoAgua(idImovel);
								
								Short hidrometro = null;
								if ( existeHidrometro != null && existeHidrometro == true ){
									//Existe Hidrometro
									hidrometro = new Short("1");
									
								}else{
									//Nao Existe Hidrometro
									hidrometro = new Short("2");
									
								}
								helper.setHidrometro(hidrometro);
								
								// se ja existe um objeto igual a ele entao soma os
								// valores e as quantidades ja existentes.
								// um objeto eh igual ao outro se ele tem todos as
								// informacos iguals 
								
								Object[] objCredito = 
									repositorioGerencialFaturamento.getValorCreditoIncluidoCanceladoCategoria(idImovel, 
										sistemaParametro.getAnoMesFaturamento(), anoMesRefConta, contaCategoria.getComp_id().getCategoria().getId().intValue());
								
								Object[] objDebito = repositorioGerencialFaturamento.getValorDebitoIncluidoCanceladoCategoria(idImovel, 
										sistemaParametro.getAnoMesFaturamento(), anoMesRefConta, contaCategoria.getComp_id().getCategoria().getId().intValue());
								
								BigDecimal valorCreditoConta2ou3ou4 = 
									repositorioGerencialFaturamento.getValorCredito2ou3IncluidoCanceladoCategoria((Integer) retorno[0], contaCategoria.getComp_id().getCategoria().getId().intValue());
								
								BigDecimal valorDebitoConta2ou3ou4 = 
									repositorioGerencialFaturamento.getValorDebito2ou3IncluidoCanceladoCategoria((Integer) retorno[0], contaCategoria.getComp_id().getCategoria().getId().intValue());

								
								BigDecimal valorCreditoCancelado = (BigDecimal) objCredito[0];
								BigDecimal valorCreditoIncluido = (BigDecimal) objCredito[1];
								
								BigDecimal valorDebitoCancelado = (BigDecimal) objDebito[0];
								BigDecimal valorDebitoIncluido = (BigDecimal) objDebito[1];
								
								if (listaSimplificadaReFaturamento.contains(helper)) {
	
									int posicao = listaSimplificadaReFaturamento.indexOf(helper);
									ResumoReFaturamentoNovoHelper jaCadastrado = 
										(ResumoReFaturamentoNovoHelper) listaSimplificadaReFaturamento.get(posicao);
									// Somatorios
									
									// Retificadas
	
									if (tipoConta == 1 || tipoContaAnt == 1) {
										
										Object[] valoresContaRetificada = repositorioGerencialFaturamento.getValorAguaEsgotoIncluidoCanceladoCategoria(
												idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento() , 
												contaCategoria.getComp_id().getCategoria().getId().intValue(), 
												contaCategoria.getComp_id().getSubcategoria().getId().intValue());

										jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add((BigDecimal) valoresContaRetificada[0]));
										jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() + (Integer) valoresContaRetificada[1]);
												
										jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add((BigDecimal) valoresContaRetificada[2]));
										jaCadastrado.setVoIncludoAgua(jaCadastrado.getVoIncludoAgua() + (Integer) valoresContaRetificada[3]);
												
										jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add((BigDecimal) valoresContaRetificada[4]));
										jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() + (Integer) valoresContaRetificada[5]);
												
										jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add((BigDecimal) valoresContaRetificada[6]));
										jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() + (Integer) valoresContaRetificada[7]);
																						
										//Valor de Debito
										jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoCancelado) );
										valorCanceladoServico4menos1.add(valorDebitoCancelado);
										jaCadastrado.setVlIncluidoOutros(jaCadastrado.getVlIncluidoOutros().add(valorDebitoIncluido));
										
										//Valor Credito
										jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoCancelado));
										jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add(valorCreditoIncluido));
										
										jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(BigDecimal.ZERO));
										jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlIncluidoImpostos().add(BigDecimal.ZERO));																						
									} else
									
									//Canceladas por Retificacao
									if (tipoConta == 4) {
										
										jaCadastrado.setQtContasRetificadas(jaCadastrado.getQtContasRetificadas()+1 );
										
										if ( jaCadastrado.getIcExistenciaContaCanceladaRetificacao() != null ){
										
											icExistContaCancRetficacao = jaCadastrado.getIcExistenciaContaCanceladaRetificacao()
																				.compareTo(new Short("2"));
										
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
									
													jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorAgua));
													jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add(valorEsgoto));
													jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
													valorCanceladoServico2ou3.add(valorDebitoConta2ou3ou4);
													jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
													jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(BigDecimal.ZERO));
													jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() +consumoAgua);
													jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() +consumoEsgoto);
											}
											
										}
									}else
									
									//Canceladas
									if(tipoConta == 3 && amContabil.equals(sistemaParametro.getAnoMesFaturamento())){
										
										jaCadastrado.setQtContasCanceladas(jaCadastrado.getQtContasCanceladas()+1);
										jaCadastrado.setVlCanceladoAgua(jaCadastrado.getVlCanceladoAgua().add(valorAgua));
										jaCadastrado.setVlCanceladoEsgoto(jaCadastrado.getVlCanceladoEsgoto().add(valorEsgoto));
										jaCadastrado.setVlCanceladoOutro(jaCadastrado.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
										valorCanceladoServico2ou3.add(valorDebitoConta2ou3ou4);
										jaCadastrado.setVlCanceladoCreditos(jaCadastrado.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
										jaCadastrado.setVlCanceladoImpostos(jaCadastrado.getVlCanceladoImpostos().add(BigDecimal.ZERO));
										jaCadastrado.setVoCanceladoAgua(jaCadastrado.getVoCanceladoAgua() +consumoAgua);
										jaCadastrado.setVoCanceladoEsgoto(jaCadastrado.getVoCanceladoEsgoto() +consumoEsgoto);
										
									}else
		        						
									// Incluidas
									if((tipoConta == 2 || tipoContaAnt == 2) && amContabil.equals(sistemaParametro.getAnoMesFaturamento()) ){
										
										jaCadastrado.setQtContasIncluidas(jaCadastrado.getQtContasIncluidas()+1);
										
										jaCadastrado.setVlIncluidoAgua(jaCadastrado.getVlIncluidoAgua().add(valorAgua));
										jaCadastrado.setVlIncluidoEsgoto(jaCadastrado.getVlIncluidoEsgoto().add(valorEsgoto));
										jaCadastrado.setVlIncluidoOutros(jaCadastrado.getVlIncluidoOutros().add(valorDebitoConta2ou3ou4));
										jaCadastrado.setVlIncluidoCreditos(jaCadastrado.getVlIncluidoCreditos().add(valorCreditoConta2ou3ou4));
										jaCadastrado.setVlIncluidoImpostos(jaCadastrado.getVlIncluidoImpostos().add(BigDecimal.ZERO));
										jaCadastrado.setVoIncludoAgua(jaCadastrado.getVoIncludoAgua() +consumoAgua);
										jaCadastrado.setVoIncluidoEsgoto(jaCadastrado.getVoIncluidoEsgoto() +consumoEsgoto);
									
									}
									
									// AnoMesReferencia
									jaCadastrado.setAnoMesReferencia(jaCadastrado.getAnoMesReferencia());
									jaCadastrado.setAnoMesReferenciaConta(jaCadastrado.getAnoMesReferenciaConta());
	
								} else {
									
			    					// Somatorios
									
									//[SB0003]
									if (tipoConta == 1 || tipoContaAnt == 1) {
											
										helper.setIcExistenciaContaCanceladaRetificacao(new Short("1"));
										
										// Retificada
										Object[] valoresContaRetificada = repositorioGerencialFaturamento.getValorAguaEsgotoIncluidoCanceladoCategoria(
												idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento() , 
												contaCategoria.getComp_id().getCategoria().getId().intValue(), 
												contaCategoria.getComp_id().getSubcategoria().getId().intValue());
										
										if ( valoresContaRetificada == null) {
											helper.setIcExistenciaContaCanceladaRetificacao(new Short ("2"));
										}
										
										if ( helper.getIcExistenciaContaCanceladaRetificacao() != null ){
											
											icExistContaCancRetficacao = helper.getIcExistenciaContaCanceladaRetificacao()
																					.compareTo(new Short("1"));
											
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
												
												helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add((BigDecimal) valoresContaRetificada[0]));
												helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() + (Integer) valoresContaRetificada[1]);
														
												helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add((BigDecimal) valoresContaRetificada[2]));
												helper.setVoIncludoAgua(helper.getVoIncludoAgua() + (Integer) valoresContaRetificada[3]);
														
												helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add((BigDecimal) valoresContaRetificada[4]));
												helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() + (Integer) valoresContaRetificada[5]);
														
												helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add((BigDecimal) valoresContaRetificada[6]));
												helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() + (Integer) valoresContaRetificada[7]);
																								
												//Valor de Debito
												helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoCancelado) );
												valorCanceladoServico4menos1.add(valorDebitoCancelado);
												helper.setVlIncluidoOutros(helper.getVlIncluidoOutros().add(valorDebitoIncluido));
												
												//Valor Credito
												helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoCancelado));
												helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add(valorCreditoIncluido));
												
												helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(BigDecimal.ZERO));
												helper.setVlCanceladoImpostos(helper.getVlIncluidoImpostos().add(BigDecimal.ZERO));
												
//												AnoMesReferencia
												helper.setAnoMesReferencia(helper.getAnoMesReferencia());
												helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
												listaSimplificadaReFaturamento.add(helper);
											
											}
										}
										
									}else
									
									//Canceladas por Retificacao
									if (tipoConta == 4) {
										
										// Analisar
										helper.setQtContasRetificadas(helper.getQtContasRetificadas() + 1);
										
										helper.setIcExistenciaContaCanceladaRetificacao(new Short("1"));
										
										List valoresContaRetificada = this.repositorioGerencialFaturamento
										.getValorAnteriorContaRetificadaCategoria(idImovel, (Integer) retorno[18], sistemaParametro.getAnoMesFaturamento(), 1, contaCategoria.getComp_id().getCategoria().getId().intValue());
										
										if ( valoresContaRetificada == null || valoresContaRetificada.isEmpty() 
												|| valoresContaRetificada.size() == 0 ){
											
											helper.setIcExistenciaContaCanceladaRetificacao(new Short("2"));
											
										}
										
										if ( helper.getIcExistenciaContaCanceladaRetificacao() != null ){
																													
											
											icExistContaCancRetficacao = helper.getIcExistenciaContaCanceladaRetificacao()
																					.compareTo(new Short("2"));
											
											if ( icExistContaCancRetficacao != null && icExistContaCancRetficacao == 0 ){
												
												helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorAgua));
												helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add(valorEsgoto));
												helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
												valorCanceladoServico2ou3.add(valorDebitoConta2ou3ou4);
												helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
												helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(BigDecimal.ZERO));
												helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() +consumoAgua);
												helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() +consumoEsgoto);
												
//												AnoMesReferencia
												helper.setAnoMesReferencia(helper.getAnoMesReferencia());
												helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
												listaSimplificadaReFaturamento.add(helper);
																								
											}
										}
	
									}else
									
                                    // Canceladas
									if(tipoConta == 3 && amContabil.equals(sistemaParametro.getAnoMesFaturamento())){
										
										helper.setQtContasCanceladas( helper.getQtContasCanceladas() + 1);
										
										helper.setVlCanceladoAgua(helper.getVlCanceladoAgua().add(valorAgua));
										helper.setVlCanceladoEsgoto(helper.getVlCanceladoEsgoto().add(valorEsgoto));
										helper.setVlCanceladoOutro(helper.getVlCanceladoOutro().add(valorDebitoConta2ou3ou4));
										valorCanceladoServico2ou3.add(valorDebitoConta2ou3ou4);
										helper.setVlCanceladoCreditos(helper.getVlCanceladoCreditos().add(valorCreditoConta2ou3ou4));
										helper.setVlCanceladoImpostos(helper.getVlCanceladoImpostos().add(BigDecimal.ZERO));
										helper.setVoCanceladoAgua(helper.getVoCanceladoAgua() +consumoAgua);
										helper.setVoCanceladoEsgoto(helper.getVoCanceladoEsgoto() +consumoEsgoto);
										
//										AnoMesReferencia
										helper.setAnoMesReferencia(helper.getAnoMesReferencia());
										helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
										listaSimplificadaReFaturamento.add(helper);
										
									}else
		        						
									// Incluidas
									if((tipoConta == 2 || tipoContaAnt == 2) && amContabil.equals(sistemaParametro.getAnoMesFaturamento()) ){
										
										helper.setQtContasIncluidas( helper.getQtContasIncluidas() + 1);
										
										helper.setVlIncluidoAgua(helper.getVlIncluidoAgua().add(valorAgua));
										helper.setVlIncluidoEsgoto(helper.getVlIncluidoEsgoto().add(valorEsgoto));
										helper.setVlIncluidoOutros(helper.getVlIncluidoOutros().add(valorDebitoConta2ou3ou4));
										helper.setVlIncluidoCreditos(helper.getVlIncluidoCreditos().add(valorCreditoConta2ou3ou4));
										helper.setVlIncluidoImpostos(helper.getVlIncluidoImpostos().add(BigDecimal.ZERO));
										helper.setVoIncludoAgua(helper.getVoIncludoAgua() +consumoAgua);
										helper.setVoIncluidoEsgoto(helper.getVoIncluidoEsgoto() +consumoEsgoto);
									
										//AnoMesReferencia
										helper.setAnoMesReferencia(helper.getAnoMesReferencia());
										helper.setAnoMesReferenciaConta(helper.getAnoMesReferenciaConta());
										listaSimplificadaReFaturamento.add(helper);
									} 
							}
							

						} //fim do for de conta categoria 
						
					} // fim do if
							
				}//fim do for
					
				if (qtRegistros > resumoReFaturamentoContas.size()) {
					flagTerminou = true;
				} else {
					indice = indice + qtRegistros;
				}
	
			} //do while
			
			for (int i = 0; i < listaSimplificadaReFaturamento.size(); i++) {
				
				ResumoReFaturamentoNovoHelper helper = (ResumoReFaturamentoNovoHelper) listaSimplificadaReFaturamento
						.get(i);
				
				this.repositorioGerencialFaturamento.inserirResumoReFaturamentoNovo(helper);
				
			}
				
			if (listaSimplificadaReFaturamento.size() > 0 ) {
				System.out.println("final gravação dos dados");
			}
			
			System.out.println("VALOR CANCELADO DAS CONTAS 4 MENOS 1: " + valorCanceladoServico4menos1);
			System.out.println("VALOR CANCELADO DAS CONTAS 3: " + valorCanceladoServico2ou3);
			
			getControladorBatch().encerrarUnidadeProcessamentoBatch(null, idUnidadeIniciada, false);
				
		} catch (Exception ex) {
			logger.error(" ERRO NO SETOR " + idSetor+" PROCESSANDO RESUMO REFATURAMENTO", ex);
			getControladorBatch().encerrarUnidadeProcessamentoBatch(ex,	idUnidadeIniciada, true);
			throw new EJBException(ex);
		}
	}
	
	
	public Collection pesquisarContaCategoria(Integer idConta){
		
		Collection<IContaCategoria> colContaCategoria = null;
		
		try {
			
			colContaCategoria = repositorioFaturamento.pesquisarContaCategoria(idConta);
			
			boolean residencial = false;
			boolean comercial = false;
			boolean industrial = false;
			boolean publico = false;
			
			for (Iterator iter = colContaCategoria.iterator(); iter.hasNext();) {
				ContaCategoria contaCategoria = (ContaCategoria) iter.next();
				
				if (contaCategoria.getComp_id().getCategoria().getId().equals(Categoria.RESIDENCIAL)){
					residencial = true;
				}
				else if (contaCategoria.getComp_id().getCategoria().getId().equals(Categoria.COMERCIAL)){
					comercial = true;
				}
				else if(contaCategoria.getComp_id().getCategoria().getId().equals(Categoria.INDUSTRIAL)){
					industrial = true;
				}
				else{
					publico = true;
				}
			}
			
			Subcategoria subcategoria = new Subcategoria();
			subcategoria.setId(0);
			
			if (!residencial){
				
				Categoria categoria = new Categoria();
				categoria.setId(Categoria.RESIDENCIAL);
				ContaCategoriaPK pk = new ContaCategoriaPK();
				pk.setCategoria(categoria);
				pk.setSubcategoria(subcategoria);
				ContaCategoria contaCategoria = new ContaCategoria();
				contaCategoria.setComp_id(pk);
				
				colContaCategoria.add(contaCategoria);
				
			}
			
			if (!comercial){
				
				Categoria categoria = new Categoria();
				categoria.setId(Categoria.COMERCIAL);
				ContaCategoriaPK pk = new ContaCategoriaPK();
				pk.setCategoria(categoria);
				pk.setSubcategoria(subcategoria);
				ContaCategoria contaCategoria = new ContaCategoria();
				contaCategoria.setComp_id(pk);
				
				colContaCategoria.add(contaCategoria);
			}
			
			if(!industrial){
				
				Categoria categoria = new Categoria();
				categoria.setId(Categoria.INDUSTRIAL);
				ContaCategoriaPK pk = new ContaCategoriaPK();
				pk.setCategoria(categoria);
				pk.setSubcategoria(subcategoria);
				ContaCategoria contaCategoria = new ContaCategoria();
				contaCategoria.setComp_id(pk);
				
				colContaCategoria.add(contaCategoria);
			}
			
			if(!publico){
				
				Categoria categoria = new Categoria();
				categoria.setId(Categoria.PUBLICO);
				ContaCategoriaPK pk = new ContaCategoriaPK();
				pk.setCategoria(categoria);
				pk.setSubcategoria(subcategoria);
				ContaCategoria contaCategoria = new ContaCategoria();
				contaCategoria.setComp_id(pk);
				
				colContaCategoria.add(contaCategoria);
			}
			
		} catch (ErroRepositorioException e) {
			e.printStackTrace();
		}
		
		return colContaCategoria;
	}    
}