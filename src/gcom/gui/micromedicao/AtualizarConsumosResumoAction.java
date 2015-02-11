package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarConsumosResumoAction extends GcomAction {
	
	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarConsumoResumo");

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_BAIRRO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		String idImovel = (String) sessao.getAttribute("idImovel");

		String dataLeituraAnteriorInformada = leituraConsumoActionForm.getDataLeituraAnteriorFaturamento();
		String leituraAnterior = leituraConsumoActionForm.getLeituraAnteriorFaturamento();
		String dataLeituraAtualInformada = leituraConsumoActionForm.getDataLeituraAtualInformada();
		String leituraAtual = leituraConsumoActionForm.getLeituraAtualInformada();
		String consumoInformado = leituraConsumoActionForm.getConsumoInformado();
		String indicadorConfirmacao = leituraConsumoActionForm.getConfirmacao();

		Integer idMotivoInterferenciaTipo = leituraConsumoActionForm.getMotivoInterferenciaTipo();

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) sessao.getAttribute("faturamentoGrupo");

		if (indicadorConfirmacao == null || indicadorConfirmacao.trim().equals("")) {
			indicadorConfirmacao = "0";
		}
		
		String idAnormalidadeLeitura = leituraConsumoActionForm.getIdAnormalidade();

		String tipoMedicao = (String) sessao.getAttribute("tipoMedicao");
		Imovel imovel = ((ImovelMicromedicao) sessao.getAttribute("imovelMicromedicaoDadosResumo")).getImovel();
		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) sessao.getAttribute("medicaoHistoricoAnoMesAtual");

		boolean alterouAnormalidade = existeAltracaoAnormalidade(idAnormalidadeLeitura,	medicaoHistorico);

		LeituraSituacao leituraSituacao = new LeituraSituacao();

		if (imovel != null) {
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

			Date dataLeituraAtual = null;
			Date dataLeituraAnterior = null;

			String anoMesReferencia = "" + medicaoHistorico.getAnoMesReferencia();

			if (isImovelHidrometrado(imovel)) {

				if (dataLeituraAtualInformada != null && !dataLeituraAtualInformada.equals("")) {

					try {
						dataLeituraAtual = dataFormatada.parse(dataLeituraAtualInformada);
					} catch (ParseException ex) {
						throw new ActionServletException("erro.sistema");
					}
					Calendar dataAtual = new GregorianCalendar();
					dataAtual.setTime(dataLeituraAtual);

					String anoMesAtual = "" + dataAtual.get(Calendar.YEAR);
					String mes = "" + (dataAtual.get(Calendar.MONTH) + 1);

					if (!(mes.length() == 2)) {
						mes = "0" + mes;
					}

					anoMesAtual += mes;

					boolean mesAnoValido = fachada.validaDataFaturamentoIncompativel(anoMesReferencia, anoMesAtual);
					
					if (!mesAnoValido) {
						sessao.setAttribute("nomeCampo", "dataLeituraAtual");
						throw new ActionServletException("atencao.faturamento_data_incompativel", null,"Data de Leitura Atual Informada");
					}

					if (dataLeituraAnteriorInformada != null && !dataLeituraAnteriorInformada.equals("")) {
						try {
							if (Util.validarDiaMesAno(dataLeituraAnteriorInformada)) {
								throw new ActionServletException("atencao.data.invalida", null, "Data Anterior");
							}
							dataLeituraAnterior = dataFormatada.parse(dataLeituraAnteriorInformada);
						} catch (ParseException ex) {
							throw new ActionServletException("erro.sistema");
						}
						Calendar dataAnterior = new GregorianCalendar();
						dataAnterior.setTime(dataLeituraAnterior);

						if (dataAtual.compareTo(dataAnterior) < 0) {
							sessao.setAttribute("nomeCampo", "dataLeituraAtual");
							throw new ActionServletException("atencao.faturamento_data_atual_inferior_data_anterior");
						}
					}
					medicaoHistorico.setDataLeituraAtualFaturamento(dataLeituraAtual);
					medicaoHistorico.setDataLeituraAtualInformada(dataLeituraAtual);
				}

				if (dataLeituraAnteriorInformada != null && !dataLeituraAnteriorInformada.equals("")) {
					try {
						if (Util.validarDiaMesAno(dataLeituraAnteriorInformada)) {
							throw new ActionServletException("atencao.data.invalida", null, "Data Anterior");
						}
						dataLeituraAnterior = dataFormatada.parse(dataLeituraAnteriorInformada);
					} catch (ParseException ex) {
						throw new ActionServletException("erro.sistema");
					}
					
					Calendar dataAnterior = new GregorianCalendar();
					dataAnterior.setTime(dataLeituraAnterior);
					String anoMesAnterior = ""+ dataAnterior.get(Calendar.YEAR);
					String mesAnterior = ""	+ (dataAnterior.get(Calendar.MONTH) + 1);

					if (!(mesAnterior.length() == 2)) {
						mesAnterior = "0" + mesAnterior;
					}

					anoMesAnterior += mesAnterior;

					boolean anoMesInferiorValido = fachada.validaDataFaturamentoIncompativelInferior(anoMesReferencia, anoMesAnterior);

					try {
						if (!anoMesInferiorValido && !fachada.verificarInstalacaoSubstituicaoHidrometro(new Integer(idImovel), new MedicaoTipo(new Integer(tipoMedicao)))) {
							sessao.setAttribute("nomeCampo", "dataLeituraAnterior");
							throw new ActionServletException("atencao.faturamento_data_incompativel", null, "Data de Leitura Anterior de Faturamento");
						}
					} catch (NumberFormatException e) {
					} catch (ControladorException e) {
					}

					medicaoHistorico.setDataLeituraAnteriorFaturamento(dataLeituraAnterior);
				}

				if ((obterNumeroDigitosLeituraHidrometro(imovel) >= leituraAtual.length()) 
					&& (obterNumeroDigitosLeituraHidrometro(imovel) >= leituraAnterior.length())) {

					if (!("" + medicaoHistorico.getLeituraAnteriorFaturamento()).equals(leituraAnterior)) {
						LeituraSituacao leituraSituacaoAnterior = new LeituraSituacao(LeituraSituacao.LEITURA_ALTERADA);
						medicaoHistorico.setLeituraSituacaoAnterior(leituraSituacaoAnterior);
					}
					medicaoHistorico.setLeituraAnteriorFaturamento(new Integer(leituraAnterior));
				
				} else {
					sessao.setAttribute("nomeCampo", "leituraAnterior");
					throw new ActionServletException("atencao.digitos.leitura.maior.hidrometro");
				}

				if (possuiAnormalidadeLeitura(idAnormalidadeLeitura)) {
					
					Collection anormalidadeLeituraEncontrada = obterAnormalidadeLeitura(fachada, idAnormalidadeLeitura);
					
					if (anormalidadeLeituraEncontrada != null && !anormalidadeLeituraEncontrada.isEmpty()) {
						medicaoHistorico.setLeituraAnormalidadeInformada(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)));
						medicaoHistorico.setLeituraAnormalidadeFaturamento(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)));
					} else {
						sessao.setAttribute("nomeCampo", "idAnormalidade");
						throw new ActionServletException("atencao.pesquisa_inexistente", null, "Anormalidade de Leitura");
					}
				} else {
					medicaoHistorico.setLeituraAnormalidadeInformada(null);
					medicaoHistorico.setLeituraAnormalidadeFaturamento(null);
				}

				if (obterNumeroDigitosLeituraHidrometro(imovel) >= leituraAtual.length()) {
					medicaoHistorico.setLeituraAtualInformada(leituraAtual.equals("") ? null : new Integer(leituraAtual));
				
				} else {
					sessao.setAttribute("nomeCampo", "leituraAtual");
					throw new ActionServletException("atencao.digitos.leitura.maior.hidrometro");
				}

				leituraSituacao = obterSituacaoLeitura(leituraAtual, indicadorConfirmacao, leituraSituacao);
				medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);

				if (consumoInformado != null && !consumoInformado.equalsIgnoreCase("")) {
					medicaoHistorico.setNumeroConsumoInformado(new Integer(consumoInformado));
				}
			} else {
				if (possuiAnormalidadeLeitura(idAnormalidadeLeitura)) {
					Collection anormalidadeLeituraEncontrada = obterAnormalidadeLeitura(fachada, idAnormalidadeLeitura);

					if (anormalidadeLeituraEncontrada != null && !anormalidadeLeituraEncontrada.isEmpty()) {

						if (((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)).getIndicadorImovelSemHidrometro().equals(ConstantesSistema.NAO)) {
							sessao.setAttribute("nomeCampo", "idAnormalidade");
							throw new ActionServletException("atencao.leitura.anormalidade.nao.permitida");
						}
						imovel.setLeituraAnormalidade((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0));
					} else {
						sessao.setAttribute("nomeCampo", "idAnormalidade");
						throw new ActionServletException("atencao.pesquisa_inexistente", null, "Anormalidade de Leitura");
					}
				} else {
					imovel.setLeituraAnormalidade(null);
					imovel.setUltimaAlteracao(new Date());
				}

			}
			boolean ligacaoAgua = (Boolean) sessao.getAttribute("ligacaoAgua");

			Integer idLeituraAnormalidade = null;
			if (possuiAnormalidadeLeitura(idAnormalidadeLeitura)) {
				idLeituraAnormalidade = new Integer(idAnormalidadeLeitura);
			}

			String confirmado = httpServletRequest.getParameter("confirmado");

			if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")) {
				int consumo = 0;
				int consumoMedioMes = 0;

				if (leituraConsumoActionForm.getConsumoMedido() != null && !leituraConsumoActionForm.getConsumoMedido().equals("")) {
					consumoMedioMes = new Integer(leituraConsumoActionForm.getConsumoMedido());
				}

				imovel.setId(new Integer(idImovel));
				int qtdeEconomias = fachada.obterQuantidadeEconomias(imovel);

				boolean houveIntslacaoHidrometro = false;
				try {
					MedicaoTipo medicao = new MedicaoTipo();

					if (tipoMedicao != null) {
						medicao.setId(new Integer(tipoMedicao));
						houveIntslacaoHidrometro = fachada.verificarInstalacaoSubstituicaoHidrometro(imovel.getId(), medicao);
					}
				} catch (ControladorException e) {
					e.printStackTrace();
				}

				int idLigacaoAgua = fachada.verificarTipoLigacao(imovel);
				int[] consumoMedioImovel = fachada.obterVolumeMedioAguaEsgoto(imovel.getId(),
						sistemaParametro.getAnoMesFaturamento(), idLigacaoAgua, houveIntslacaoHidrometro);

				if (consumoMedioImovel != null && consumoMedioImovel.length > 0) {

					if (consumoInformado != null && !consumoInformado.equals("")) {
						consumo = new Integer(consumoInformado);
					}

					int consumoMedio = consumoMedioImovel[0];
					int consumoMedio5 = consumoMedio * 5;
					int qtdeEconomias30 = qtdeEconomias * 30;
					
					if (consumo > consumoMedio5 && consumo > qtdeEconomias30 && consumo > consumoMedioMes) {
						httpServletRequest.setAttribute("caminhoActionConclusao", "/gsan/atualizarConsumoResumoAction.do");
						return montarPaginaConfirmacao("atencao.invalido.consumo", httpServletRequest, actionMapping, null, null);
					}
				}

			}
			Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

			MotivoInterferenciaTipo motivoInterferenciaTipo = new MotivoInterferenciaTipo();

			if (idMotivoInterferenciaTipo != null && idMotivoInterferenciaTipo > 0) {
				motivoInterferenciaTipo.setId(idMotivoInterferenciaTipo);
			}

			Integer index = null;

			if (sessao.getAttribute("index") != null) {
				index = (Integer) sessao.getAttribute("index");
			}

			Collection colecaoIdsImovel = (Collection) sessao.getAttribute("colecaoIdsImovelTotal");

			if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()	&& index != null) {

				((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().setUsuarioAlteracao(usuarioLogado);
			}

			if(leituraSituacao.getId().equals(LeituraSituacao.CONFIRMADA)){
				fachada.calcularLeituraConfirmada(new Integer(leituraAnterior), new Integer(leituraAtual), leituraSituacao, new Integer(idImovel), 
						medicaoHistorico.getAnoMesReferencia(), faturamentoGrupo, sistemaParametro, dataLeituraAtualInformada, idLeituraAnormalidade, alterouAnormalidade);
			} else {

				fachada.atualizarLeituraConsumoResumido(new Integer(idImovel), medicaoHistorico.getMesAno().toString(), dataLeituraAnteriorInformada, 
						leituraAnterior, dataLeituraAtualInformada, leituraAtual, consumoInformado, ligacaoAgua, idLeituraAnormalidade, leituraSituacao.getId(), 
						faturamentoGrupo, usuarioLogado, alterouAnormalidade, motivoInterferenciaTipo, new Integer(tipoMedicao));
			}
			httpServletRequest.setAttribute("sucesso", true);
		}
		return retorno;
	}

	private int obterNumeroDigitosLeituraHidrometro(Imovel imovel) {
		return imovel.getHidrometroInstalacaoHistorico() == null ? 
				imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue()
				: imovel.getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue();
	}

	private boolean isImovelHidrometrado(Imovel imovel) {
		return imovel.getHidrometroInstalacaoHistorico() != null || (imovel.getLigacaoAgua() != null && imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico() != null);
	}

	private boolean possuiAnormalidadeLeitura(String idAnormalidadeLeitura) {
		return idAnormalidadeLeitura != null && !idAnormalidadeLeitura.trim().equals("");
	}

	@SuppressWarnings("rawtypes")
	private Collection obterAnormalidadeLeitura(Fachada fachada, String idAnormalidadeLeitura) {
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, idAnormalidadeLeitura));
		Collection anormalidadeLeituraEncontrada = fachada.pesquisar(filtroLeituraAnormalidade,	LeituraAnormalidade.class.getName());

		return anormalidadeLeituraEncontrada;
	}

	private LeituraSituacao obterSituacaoLeitura(String leituraAtual, String indicadorConfirmacao, LeituraSituacao leituraSituacao) {
		
		if (!leituraAtual.equals("") && new Integer(leituraAtual).intValue() == 0) {
			leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
		} else {
			if (indicadorConfirmacao.equals(ConstantesSistema.CONFIRMADA)) {
				leituraSituacao.setId(LeituraSituacao.CONFIRMADA);
			} else {
				leituraSituacao.setId(LeituraSituacao.REALIZADA);
			}
		}
		return leituraSituacao;
	}

	private boolean existeAltracaoAnormalidade(String idAnormalidadeLeitura, MedicaoHistorico medicaoHistorico) {
		boolean existeAlteracaoAnormalidade = false;
		
		if (medicaoHistorico.getLeituraAnormalidadeFaturamento() != null) {

			if (medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() != null && idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {
				if (!medicaoHistorico.getLeituraAnormalidadeFaturamento().getId().toString().equalsIgnoreCase(idAnormalidadeLeitura)) {
					existeAlteracaoAnormalidade = true;
				}
			
			} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() != null && idAnormalidadeLeitura == null) {
				existeAlteracaoAnormalidade = true;

			} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento().getId() == null	&& idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {
				existeAlteracaoAnormalidade = true;
			}
			
		} else if (medicaoHistorico.getLeituraAnormalidadeFaturamento() == null	&& idAnormalidadeLeitura != null && !idAnormalidadeLeitura.equals("")) {
			existeAlteracaoAnormalidade = true;
		}
		return existeAlteracaoAnormalidade;
	}

}
