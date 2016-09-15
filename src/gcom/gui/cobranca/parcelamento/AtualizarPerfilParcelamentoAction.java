package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 12/05/2006
 */
public class AtualizarPerfilParcelamentoAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 12/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String idPerfilParcelamento = (String) sessao
				.getAttribute("idRegistroAtualizacao");
		String numeroResolucaoDiretoria = atualizarParcelamentoPerfilActionForm
				.getNumeroResolucaoDiretoria();

		FiltroParcelamentoPerfil filtroParcelamentoPerfil = new FiltroParcelamentoPerfil();
		// Seta o filtro para buscar o ParcelamentoPerfil na base
		// e recuperar o idImovelSituacaoTipo,idImovelPerfil e o idSubcategoria
		filtroParcelamentoPerfil.adicionarParametro(new ParametroSimples(
				FiltroParcelamentoPerfil.ID, idPerfilParcelamento));
		filtroParcelamentoPerfil
				.adicionarCaminhoParaCarregamentoEntidade("imovelSituacaoTipo");
		filtroParcelamentoPerfil
				.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroParcelamentoPerfil
				.adicionarCaminhoParaCarregamentoEntidade("subcategoria");
		filtroParcelamentoPerfil
				.adicionarCaminhoParaCarregamentoEntidade("categoria");
		// Procura o ParcelamentoPerfil na base
		ParcelamentoPerfil parcelamentoPerfilNaBase = null;
		parcelamentoPerfilNaBase = (ParcelamentoPerfil) ((List) (fachada
				.pesquisar(filtroParcelamentoPerfil, ParcelamentoPerfil.class
						.getName()))).get(0);

		Integer idImovelSituacaoTipo = parcelamentoPerfilNaBase
				.getImovelSituacaoTipo().getId();

		Integer idImovelPerfil = null;
		if (parcelamentoPerfilNaBase.getImovelPerfil() != null
				&& !parcelamentoPerfilNaBase.getImovelPerfil().equals("")) {
			idImovelPerfil = parcelamentoPerfilNaBase.getImovelPerfil().getId();
		}

		Integer idSubcategoria = null;
		if (parcelamentoPerfilNaBase.getSubcategoria() != null
				&& !parcelamentoPerfilNaBase.getSubcategoria().equals("")) {
			idSubcategoria = parcelamentoPerfilNaBase.getSubcategoria().getId();
		}

		Integer idCategoria = null;
		if (parcelamentoPerfilNaBase.getCategoria() != null
				&& !parcelamentoPerfilNaBase.getCategoria().equals("")) {
			idCategoria = parcelamentoPerfilNaBase.getCategoria().getId();
		}

		String percentualDescontoAcrescimoMulta = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimoMulta() != null) {
			percentualDescontoAcrescimoMulta = atualizarParcelamentoPerfilActionForm
					.getPercentualDescontoAcrescimoMulta().toString().replace(",", ".");
		}
		
		String percentualDescontoAcrescimoJurosMora = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimoJurosMora() != null) {
			percentualDescontoAcrescimoJurosMora = atualizarParcelamentoPerfilActionForm
					.getPercentualDescontoAcrescimoJurosMora().toString().replace(",", ".");
		}
		
		String percentualDescontoAcrescimoAtualizacaoMonetaria = null;
		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoAcrescimoAtualizacaoMonetaria() != null) {
			percentualDescontoAcrescimoAtualizacaoMonetaria = atualizarParcelamentoPerfilActionForm
					.getPercentualDescontoAcrescimoAtualizacaoMonetaria().toString().replace(",", ".");
		}

		String percentualDescontoAcrescimoPagamentoAVista = null;
		if (atualizarParcelamentoPerfilActionForm
				.getPercentualDescontoAcrescimoPagamentoAVista() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getPercentualDescontoAcrescimoPagamentoAVista()
						.equalsIgnoreCase("")) {
			percentualDescontoAcrescimoPagamentoAVista = atualizarParcelamentoPerfilActionForm
					.getPercentualDescontoAcrescimoPagamentoAVista().toString()
					.replace(",", ".");
		}

		String percentualTarifaMinimaPrestacao = null;
		if (atualizarParcelamentoPerfilActionForm
				.getPercentualTarifaMinimaPrestacao() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getPercentualTarifaMinimaPrestacao().equalsIgnoreCase(
								"")) {
			percentualTarifaMinimaPrestacao = atualizarParcelamentoPerfilActionForm
					.getPercentualTarifaMinimaPrestacao().toString().replace(
							",", ".");
		}

		atualizaColecoesNaSessao(sessao, httpServletRequest);

		// collectionParcelamentoQuantidadeReparcelamentoHelper
		Collection collectionParcelamentoQuantidadeReparcelamentoHelper = null;
		if (sessao
				.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null) {
			collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		}

		// collectionParcelamentoDescontoInatividade
		Collection collectionParcelamentoDescontoInatividade = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null) {
			collectionParcelamentoDescontoInatividade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividade");
		}
		
		// collectionParcelamentoDescontoInatividadeAVista
		Collection collectionParcelamentoDescontoInatividadeAVista = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null) {
			collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
		}

		// collectionParcelamentoDescontoAntiguidade
		Collection collectionParcelamentoDescontoAntiguidade = null;
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null) {
			collectionParcelamentoDescontoAntiguidade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoAntiguidade");
		}

		Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = (Collection) sessao
				.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");

		Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		
		Collection collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas = (Collection) sessao
		.getAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");

		Collection collectionParcelamentoDescontoAntiguidadeLinhaRemovidas = (Collection) sessao
				.getAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");

		Collection collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas = (Collection) sessao
				.getAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");

		validacaoFinal(percentualDescontoAcrescimoMulta,
				percentualDescontoAcrescimoJurosMora,
				percentualDescontoAcrescimoAtualizacaoMonetaria,
				percentualDescontoAcrescimoPagamentoAVista,
				percentualTarifaMinimaPrestacao, httpServletRequest,
				collectionParcelamentoQuantidadeReparcelamentoHelper,
				collectionParcelamentoDescontoAntiguidade,
				collectionParcelamentoDescontoInatividade,
				idPerfilParcelamento, fachada,
				collectionParcelamentoDescontoInatividadeAVista);

		ParcelamentoPerfil parcelamentoPerfil = new ParcelamentoPerfil();

		parcelamentoPerfil.setId(new Integer(idPerfilParcelamento));

		ResolucaoDiretoria resolucaoDiretoria = null;
		if (numeroResolucaoDiretoria != null
				&& !numeroResolucaoDiretoria.equals("")) {

			FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numeroResolucaoDiretoria));
			Collection<ResolucaoDiretoria> collectionResolucaoDiretoria = fachada
					.pesquisar(filtroResolucaoDiretoria,
							ResolucaoDiretoria.class.getName());

			if (collectionResolucaoDiretoria != null
					&& !collectionResolucaoDiretoria.isEmpty()) {
				resolucaoDiretoria = (ResolucaoDiretoria) Util
						.retonarObjetoDeColecao(collectionResolucaoDiretoria);
				parcelamentoPerfil.setResolucaoDiretoria(resolucaoDiretoria);
			}
		}

		ImovelSituacaoTipo imovelSituacaoTipo = null;
		if (idImovelSituacaoTipo != null
				&& !idImovelSituacaoTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			imovelSituacaoTipo = new ImovelSituacaoTipo();
			imovelSituacaoTipo.setId(new Integer(idImovelSituacaoTipo));
			parcelamentoPerfil.setImovelSituacaoTipo(imovelSituacaoTipo);
		}

		ImovelPerfil imovelPerfil = null;
		if (idImovelPerfil != null
				&& !idImovelPerfil.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			imovelPerfil = new ImovelPerfil();
			imovelPerfil.setId(new Integer(idImovelPerfil));
			parcelamentoPerfil.setImovelPerfil(imovelPerfil);
		}

		Subcategoria subcategoria = null;
		if (idSubcategoria != null
				&& !idSubcategoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			subcategoria = new Subcategoria();
			subcategoria.setId(new Integer(idSubcategoria));
			parcelamentoPerfil.setSubcategoria(subcategoria);
		}

		Categoria categoria = null;
		if (idCategoria != null
				&& !idCategoria.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			categoria = new Categoria();
			categoria.setId(new Integer(idCategoria));
			parcelamentoPerfil.setCategoria(categoria);
		}

		if (percentualDescontoAcrescimoMulta != null
				&& !percentualDescontoAcrescimoMulta.equals("")) {

			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoMulta);

			verificarPercentualMaximo(percentual);

			parcelamentoPerfil.setPercentualDescontoAcrescimoMulta(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoAcrescimoMulta(new BigDecimal(0));
		}
		
		if (percentualDescontoAcrescimoJurosMora != null
				&& !percentualDescontoAcrescimoJurosMora.equals("")) {

			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoJurosMora);

			verificarPercentualMaximo(percentual);

			parcelamentoPerfil.setPercentualDescontoAcrescimoJurosMora(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoAcrescimoJurosMora(new BigDecimal(0));
		}
		
		if (percentualDescontoAcrescimoAtualizacaoMonetaria != null
				&& !percentualDescontoAcrescimoAtualizacaoMonetaria.equals("")) {

			BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoAtualizacaoMonetaria);

			verificarPercentualMaximo(percentual);

			parcelamentoPerfil.setPercentualDescontoAcrescimoAtualizacaoMonetaria(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoAcrescimoAtualizacaoMonetaria(new BigDecimal(0));
		}

		if (percentualDescontoAcrescimoPagamentoAVista != null
				&& !percentualDescontoAcrescimoPagamentoAVista.equals("")) {

			BigDecimal percentual = Util
					.formatarMoedaRealparaBigDecimal(percentualDescontoAcrescimoPagamentoAVista);

			verificarPercentualMaximo(percentual);

			parcelamentoPerfil.setPercentualDescontoAcrescimoPagamentoAVista(percentual);
		} else {
			parcelamentoPerfil.setPercentualDescontoAcrescimoPagamentoAVista(new BigDecimal(0));
		}

		if (percentualTarifaMinimaPrestacao != null
				&& !percentualTarifaMinimaPrestacao.equalsIgnoreCase("")) {
			parcelamentoPerfil
					.setPercentualTarifaMinimaPrestacao(new BigDecimal(
							percentualTarifaMinimaPrestacao));
		} else {
			parcelamentoPerfil
					.setPercentualTarifaMinimaPrestacao(new BigDecimal(0));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getIndicadorParcelarChequeDevolvido() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getIndicadorParcelarChequeDevolvido().equals("")) {
			parcelamentoPerfil.setIndicadorChequeDevolvido(new Short(
					atualizarParcelamentoPerfilActionForm
							.getIndicadorParcelarChequeDevolvido()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Não parcelar com cheque devolvido");
		}

		if (atualizarParcelamentoPerfilActionForm.getConsumoMinimo() != null
				&& !atualizarParcelamentoPerfilActionForm.getConsumoMinimo()
						.equals("")) {
			parcelamentoPerfil.setNumeroConsumoMinimo(new Integer(
					atualizarParcelamentoPerfilActionForm.getConsumoMinimo()));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getPercentualVariacaoConsumoMedio() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getPercentualVariacaoConsumoMedio().equals("")) {
			parcelamentoPerfil
					.setPercentualVariacaoConsumoMedio(Util
							.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm
									.getPercentualVariacaoConsumoMedio()));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getIndicadorParcelarSancoesMaisDeUmaConta() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getIndicadorParcelarSancoesMaisDeUmaConta().equals("")) {
			parcelamentoPerfil.setIndicadorSancoesUnicaConta(new Short(
					atualizarParcelamentoPerfilActionForm
							.getIndicadorParcelarSancoesMaisDeUmaConta()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Não parcelar com sanções em mais de uma conta");
		}

		if (atualizarParcelamentoPerfilActionForm.getNumeroConsumoEconomia() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getNumeroConsumoEconomia().trim().equals("")) {
			parcelamentoPerfil.setNumeroConsumoEconomia(new Integer(
					atualizarParcelamentoPerfilActionForm
							.getNumeroConsumoEconomia()));
		}

		if (atualizarParcelamentoPerfilActionForm.getNumeroAreaConstruida() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getNumeroAreaConstruida().trim().equals("")) {
			parcelamentoPerfil
					.setNumeroAreaConstruida(Util
							.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm
									.getNumeroAreaConstruida()));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getIndicadorRetroativoTarifaSocial() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getIndicadorRetroativoTarifaSocial().equals("")) {
			parcelamentoPerfil.setIndicadorRetroativoTarifaSocial(new Short(
					atualizarParcelamentoPerfilActionForm
							.getIndicadorRetroativoTarifaSocial()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Indicador de retroativo de tarifa social");
		}

		if (atualizarParcelamentoPerfilActionForm
				.getAnoMesReferenciaLimiteInferior() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getAnoMesReferenciaLimiteInferior().trim().equals("")) {
			parcelamentoPerfil
					.setAnoMesReferenciaLimiteInferior(new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm
											.getAnoMesReferenciaLimiteInferior())));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getAnoMesReferenciaLimiteSuperior() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getAnoMesReferenciaLimiteSuperior().trim().equals("")) {
			parcelamentoPerfil
					.setAnoMesReferenciaLimiteSuperior(new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(atualizarParcelamentoPerfilActionForm
											.getAnoMesReferenciaLimiteSuperior())));
		}

		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoTarifaSocial() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getPercentualDescontoTarifaSocial().trim().equals("")) {
			BigDecimal percentual = Util
					.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm
							.getPercentualDescontoTarifaSocial());
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoTarifaSocial(percentual);
		}

		if (atualizarParcelamentoPerfilActionForm
				.getParcelaQuantidadeMinimaFatura() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getParcelaQuantidadeMinimaFatura().trim().equals("")) {
			parcelamentoPerfil.setParcelaQuantidadeMinimaFatura(new Integer(
					atualizarParcelamentoPerfilActionForm
							.getParcelaQuantidadeMinimaFatura()));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getIndicadorAlertaParcelaMinima() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getIndicadorAlertaParcelaMinima().equals("")) {
			parcelamentoPerfil.setIndicadorAlertaParcelaMinima(new Short(
					atualizarParcelamentoPerfilActionForm
							.getIndicadorAlertaParcelaMinima()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Indicador de alerta de parcela mínima");
		}

		if (atualizarParcelamentoPerfilActionForm.getPercentualDescontoSancao() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getPercentualDescontoSancao().trim().equals("")) {
			BigDecimal percentual = Util
					.formatarMoedaRealparaBigDecimal(atualizarParcelamentoPerfilActionForm
							.getPercentualDescontoSancao());
			verificarPercentualMaximo(percentual);
			parcelamentoPerfil.setPercentualDescontoSancao(percentual);
		}

		if (atualizarParcelamentoPerfilActionForm.getQuantidadeEconomias() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getQuantidadeEconomias().trim().equals("")) {
			parcelamentoPerfil.setQuantidadeEconomias(new Integer(
					atualizarParcelamentoPerfilActionForm
							.getQuantidadeEconomias()));
		}

		if (atualizarParcelamentoPerfilActionForm
				.getQuantidadeMaximaReparcelamento() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getQuantidadeMaximaReparcelamento().trim().equals("")) {
			parcelamentoPerfil.setQuantidadeMaximaReparcelamento(new Integer(
					atualizarParcelamentoPerfilActionForm
							.getQuantidadeMaximaReparcelamento()));
		}

		if (atualizarParcelamentoPerfilActionForm.getIndicadorEntradaMinima() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getIndicadorEntradaMinima().equals("")) {
			parcelamentoPerfil.setIndicadorEntradaMinima(new Short(
					atualizarParcelamentoPerfilActionForm
							.getIndicadorEntradaMinima()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Indicador de entrada mínima");
		}

		if (atualizarParcelamentoPerfilActionForm.getCapacidadeHidrometro() != null
				&& !atualizarParcelamentoPerfilActionForm
						.getCapacidadeHidrometro().trim().equals("")) {
			parcelamentoPerfil.setCapacidadeHidrometro(new Short(
					atualizarParcelamentoPerfilActionForm
							.getCapacidadeHidrometro()));
		} else {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Indicador pesquisa capacidade do hidrometro");
		}
		
		
		if (atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()  != null && 
        		!atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista().trim().equals("")){
			parcelamentoPerfil.setDataLimiteDescontoPagamentoAVista(
        			Util.converteStringParaDate(atualizarParcelamentoPerfilActionForm.getDataLimiteDescontoPagamentoAVista()));
        }
        

		parcelamentoPerfil
				.setUltimaAlteracao(Util
						.converteStringParaDateHora(atualizarParcelamentoPerfilActionForm
								.getUltimaAlteracao()));

		fachada
				.atualizarPerfilParcelamento(
						parcelamentoPerfil,
						collectionParcelamentoQuantidadeReparcelamentoHelper,
						collectionParcelamentoDescontoInatividade,
						collectionParcelamentoDescontoAntiguidade,
						collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas,
						collectionParcelamentoDescontoInatividadeLinhaRemovidas,
						collectionParcelamentoDescontoAntiguidadeLinhaRemovidas,
						collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas,
						this.getUsuarioLogado(httpServletRequest),
						collectionParcelamentoDescontoInatividadeAVista,
						collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas);

		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividade");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidade");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoAntiguidadeLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVista");
		sessao.removeAttribute("collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas");

		//
		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Perfil de Parcelamento da RD de número "
							+ numeroResolucaoDiretoria
							+ " atualizado com sucesso.",
					"Realizar outra Manutenção de Perfil de Parcelamento",
					"exibirFiltrarPerfilParcelamentoAction.do?desfazer=S");
		}

		return retorno;
	}

	private void validacaoFinal(String percentualDescontoAcrescimoMulta,
			String percentualDescontoAcrescimoJurosMora,
			String percentualDescontoAcrescimoAtualizacaoMonetaria,
			String percentualDescontoAcrescimoPagamentoAVista,
			String percentualTarifaMinimaPrestacao,
			HttpServletRequest httpServletRequest,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoAntiguidade,
			Collection collectionParcelamentoDescontoInatividade,
			String idPerfilParcelamento, Fachada fachada,
			Collection collectionParcelamentoDescontoInatividadeAVista) {

		 if (percentualDescontoAcrescimoMulta == null ||
				 percentualDescontoAcrescimoMulta.equalsIgnoreCase("")){
		 httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoMulta");
		 
		 //Informe Percentual de Desconto sobre Multa		 
		 throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Multa");
		 }
		 
		 if (percentualDescontoAcrescimoJurosMora == null ||
				 percentualDescontoAcrescimoJurosMora.equalsIgnoreCase("")){
		 httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoJurosMora");
		 
		 //Informe Percentual de Desconto sobre Juros Mora	 
		 throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Juros Mora");
		 }
		 
		 if (percentualDescontoAcrescimoAtualizacaoMonetaria == null ||
				 percentualDescontoAcrescimoAtualizacaoMonetaria.equalsIgnoreCase("")){
		 httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoAtualizacaoMonetaria");
		 
		 //Informe Percentual de Desconto sobre Atualização Monetária
		 throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre Atualização Monetária");
		 }
		 
		 if (percentualDescontoAcrescimoPagamentoAVista == null ||
				 percentualDescontoAcrescimoPagamentoAVista.equalsIgnoreCase("")){
		 httpServletRequest.setAttribute("nomeCampo","percentualDescontoAcrescimoPagamentoAVista");
		 
		 //Informe Percentual de Desconto sobre os Acréscimos por Impontualidade para pagamento à vista
		 throw new ActionServletException("atencao.required", null, " Percentual de Desconto sobre os Acréscimos por Impontualidade para pagamento à vista");
		 }

		
		if (percentualTarifaMinimaPrestacao == null
				|| percentualTarifaMinimaPrestacao.equalsIgnoreCase("")) {
			httpServletRequest.setAttribute("nomeCampo",
					"percentualTarifaMinimaPrestacao");
			// Informe Percentual da Tarifa Mínima para Cálculo do Valor Mínimo
			// da Prestação
			throw new ActionServletException("atencao.required", null,
					" Percentual da Tarifa Mínima para Cálculo do Valor Mínimo da Prestação");
		}

		// [FS0002]Verificar se perfil de parcelamento já foi utilizado
		// FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
		//
		// filtroParcelamento.adicionarParametro(new ParametroSimples(
		// FiltroParcelamento.PARCELAMENTO_PERFIL_ID, idPerfilParcelamento));
		//
		// Collection colecaoParcelamento =
		// fachada.pesquisar(filtroParcelamento,
		// Parcelamento.class.getName());
		//
		// if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
		// //Perfil de Parcelamento já utilizado, não pode ser alterado nem
		// excluído.
		// throw new ActionServletException(
		// "atencao.perfil_parcelamento_ja_utilizado");
		// }
		//		

		if (collectionParcelamentoQuantidadeReparcelamentoHelper == null
				|| collectionParcelamentoQuantidadeReparcelamentoHelper
						.isEmpty()) {
			throw new ActionServletException(
			// Informe os dados de Reparcelamento Consecutivo
					"atencao.required", null, " Reparcelamento Consecutivo");
		}
		Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper
				.iterator();

		while (iterator.hasNext()) {

			ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = (ParcelamentoQuantidadeReparcelamentoHelper) iterator
					.next();
			/*
			 * if
			 * (parcelamentoQuantidadeReparcelamentoHelper.getValorMinimoPrestacao() ==
			 * null){ throw new ActionServletException( // Informe Valor
			 * Mínimo da Prestação "atencao.required", null, " Valor Mínimo
			 * da Prestação"); }
			 */
			Collection collectionParcelamentoQuantidadePrestacaoHelper = parcelamentoQuantidadeReparcelamentoHelper
					.getCollectionParcelamentoQuantidadePrestacaoHelper();

			if (collectionParcelamentoQuantidadePrestacaoHelper == null
					|| collectionParcelamentoQuantidadePrestacaoHelper
							.isEmpty()) {
				throw new ActionServletException(
				// Informações do Parcelamento por Quantidade de
				// Reparcelamentos deve ser informado
						"atencao.campo.informado", null,
						"Informações do Parcelamento por Quantidade de Reparcelamentos");
			}
		}

		// filtro para descobrir o percentual máximo de desconto permitido para
		// financiamento
		/*
		 * FiltroSistemaParametro filtroSistemaParametro = new
		 * FiltroSistemaParametro(); Collection colecaoSistemaParametros;
		 * 
		 * colecaoSistemaParametros = fachada.pesquisar( filtroSistemaParametro,
		 * SistemaParametro.class.getName()); SistemaParametro sistemaParametro =
		 * (SistemaParametro)colecaoSistemaParametros .iterator().next();
		 * BigDecimal percentualMaximoAbatimentoPermitido =
		 * sistemaParametro.getPercentualMaximoAbatimento();
		 */

		if (collectionParcelamentoDescontoAntiguidade != null
				&& !collectionParcelamentoDescontoAntiguidade.isEmpty()) {

			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
					.iterator();

			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {

				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
						.next();

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}

				if (parcelamentoDescontoAntiguidade
						.getPercentualDescontoAtivo() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Ativo
							"atencao.required", null,
							"  Percentual de Desconto Ativo");
				}
			}

		}

		if (collectionParcelamentoDescontoInatividade != null
				&& !collectionParcelamentoDescontoInatividade.isEmpty()) {

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
						.next();

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}
			}
		}
		
		if (collectionParcelamentoDescontoInatividadeAVista != null
				&& !collectionParcelamentoDescontoInatividadeAVista.isEmpty()) {

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcDesctoInativVista parcelamentoDescontoInatividade = 
					(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoSemRestabelecimento() == null) {
					throw new ActionServletException(
					// Percentual de Desconto Sem Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Sem Restabelecimento");
				}

				if (parcelamentoDescontoInatividade
						.getPercentualDescontoComRestabelecimento() == null) {
					throw new ActionServletException(
					// Informe Percentual de Desconto Com Restabelecimento
							"atencao.required", null,
							"  Percentual de Desconto Com Restabelecimento");
				}
			}
		}

	}

	private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// collectionParcelamentoDescontoAntiguidade
		if (sessao.getAttribute("collectionParcelamentoDescontoAntiguidade") != null
				&& !sessao.getAttribute(
						"collectionParcelamentoDescontoAntiguidade").equals("")) {

			Collection collectionParcelamentoDescontoAntiguidade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoAntiguidade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto ParcelamentoDescontoAntiguidade
			String vlSemRestAntiguidade = null;
			String vlComRestAntiguidade = null;
			String vlDescontoAtivo = null;

			Iterator iteratorParcelamentoDescontoAntiguidade = collectionParcelamentoDescontoAntiguidade
					.iterator();

			while (iteratorParcelamentoDescontoAntiguidade.hasNext()) {

				ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) iteratorParcelamentoDescontoAntiguidade
						.next();
				long valorTempo = parcelamentoDescontoAntiguidade
						.getUltimaAlteracao().getTime();
				vlSemRestAntiguidade = httpServletRequest
						.getParameter("vlSemRestAntiguidade" + valorTempo);
				vlComRestAntiguidade = httpServletRequest
						.getParameter("vlComRestAntiguidade" + valorTempo);
				vlDescontoAtivo = httpServletRequest
						.getParameter("vlDescontoAtivo" + valorTempo);

				// inseri essas variáveis no objeto
				// ParcelamentoDescontoAntiguidade
				BigDecimal percentualDescontoSemRestabelecimentoAntiguidade = null;
				if (vlSemRestAntiguidade != null
						&& !vlSemRestAntiguidade.equals("")) {

					percentualDescontoSemRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestAntiguidade);
				}

				BigDecimal percentualDescontoComRestabelecimentoAntiguidade = null;
				if (vlComRestAntiguidade != null
						&& !vlComRestAntiguidade.equals("")) {

					percentualDescontoComRestabelecimentoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestAntiguidade);
				}

				BigDecimal percentualDescontoAtivoAntiguidade = null;
				if (vlDescontoAtivo != null && !vlDescontoAtivo.equals("")) {

					percentualDescontoAtivoAntiguidade = Util
							.formatarMoedaRealparaBigDecimal(vlDescontoAtivo);
				}

				parcelamentoDescontoAntiguidade
						.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade
						.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoAntiguidade);
				parcelamentoDescontoAntiguidade
						.setPercentualDescontoAtivo(percentualDescontoAtivoAntiguidade);

			}

		}

		// collectionParcelamentoDescontoInatividade
		if (sessao.getAttribute("collectionParcelamentoDescontoInatividade") != null
				&& !sessao.getAttribute(
						"collectionParcelamentoDescontoInatividade").equals("")) {

			Collection collectionParcelamentoDescontoInatividade = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividade");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividade
					.iterator();

			while (iteratorParcelamentoDescontoInatividade.hasNext()) {

				ParcelamentoDescontoInatividade parcelamentoDescontoInatividade = (ParcelamentoDescontoInatividade) iteratorParcelamentoDescontoInatividade
						.next();
				long valorTempo = parcelamentoDescontoInatividade
						.getUltimaAlteracao().getTime();
				vlSemRestInatividade = httpServletRequest
						.getParameter("vlSemRestInatividade" + valorTempo);
				vlComRestInatividade = httpServletRequest
						.getParameter("vlComRestInatividade" + valorTempo);

				// insere essas variáveis no objeto
				// ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade = null;
				if (vlSemRestInatividade != null
						&& !vlSemRestInatividade.equals("")) {

					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}

				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null
						&& !vlComRestInatividade.equals("")) {

					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade
						.setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade
						.setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);

			}

		}
		
    	//collectionParcelamentoDescontoInatividadeAVista
    	if (sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista") != null
				&& !sessao.getAttribute("collectionParcelamentoDescontoInatividadeAVista").equals("")) {
	

			Collection collectionParcelamentoDescontoInatividadeAVista = (Collection) sessao
					.getAttribute("collectionParcelamentoDescontoInatividadeAVista");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String vlSemRestInatividade = null;
			String vlComRestInatividade = null;

			Iterator iteratorParcelamentoDescontoInatividade = collectionParcelamentoDescontoInatividadeAVista.iterator();
			
			while (iteratorParcelamentoDescontoInatividade.hasNext()) {
				ParcDesctoInativVista parcelamentoDescontoInatividade = 
						(ParcDesctoInativVista) iteratorParcelamentoDescontoInatividade.next();
				long valorTempo = parcelamentoDescontoInatividade.getUltimaAlteracao().getTime();
				vlSemRestInatividade = (String) httpServletRequest.getParameter("vlSemRestInatividadeAVista"+ valorTempo);
				vlComRestInatividade = httpServletRequest.getParameter("vlComRestInatividadeAVista"+ valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal percentualDescontoSemRestabelecimentoInatividade  = null;
				if (vlSemRestInatividade != null && !vlSemRestInatividade.equals("")) {
					percentualDescontoSemRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlSemRestInatividade);
				}
				
				BigDecimal percentualDescontoComRestabelecimentoInatividade = null;
				if (vlComRestInatividade != null && !vlComRestInatividade.equals("")) {
					percentualDescontoComRestabelecimentoInatividade = Util
							.formatarMoedaRealparaBigDecimal(vlComRestInatividade);
				}

				parcelamentoDescontoInatividade.
					setPercentualDescontoSemRestabelecimento(percentualDescontoSemRestabelecimentoInatividade);
				parcelamentoDescontoInatividade.
					setPercentualDescontoComRestabelecimento(percentualDescontoComRestabelecimentoInatividade);


			}

        }	        	

		// collectionParcelamentoQuantidadeReparcelamentoHelper
		/*
		 * if
		 * (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") !=
		 * null &&
		 * !sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper").equals(
		 * "")) {
		 * 
		 * Collection collectionParcelamentoQuantidadeReparcelamentoHelper =
		 * (Collection) sessao
		 * .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper"); //
		 * cria as variáveis para recuperar os parâmetros do request e jogar //
		 * no objeto ParcelamentoQuantidadeReparcelamentoHelper String
		 * vlMinPrest = null;
		 * 
		 * Iterator iteratorParcelamentoQuantidadeReparcelamentoHelper =
		 * collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
		 * 
		 * while (iteratorParcelamentoQuantidadeReparcelamentoHelper.hasNext()) {
		 * ParcelamentoQuantidadeReparcelamentoHelper
		 * parcelamentoQuantidadeReparcelamentoHelper =
		 * (ParcelamentoQuantidadeReparcelamentoHelper)
		 * iteratorParcelamentoQuantidadeReparcelamentoHelper .next(); long
		 * valorTempo =
		 * parcelamentoQuantidadeReparcelamentoHelper.getUltimaAlteracao()
		 * .getTime(); vlMinPrest = (String)
		 * httpServletRequest.getParameter("vlMinPrest" + valorTempo);
		 *  // insere essas variáveis no objeto
		 * ParcelamentoQuantidadeReparcelamentoHelper BigDecimal
		 * valorMinimoPrestacao = null; if (vlMinPrest != null &&
		 * !vlMinPrest.equals("")) { valorMinimoPrestacao =
		 * Util.formatarMoedaRealparaBigDecimal(vlMinPrest); }
		 * 
		 * parcelamentoQuantidadeReparcelamentoHelper.setValorMinimoPrestacao(valorMinimoPrestacao);
		 *  } }
		 */
	}

	// [FS0010]-Verificar Percentual Máximo
	private void verificarPercentualMaximo(BigDecimal percentual) {

		BigDecimal percentualMaximo = new BigDecimal("100");

		if (percentual.compareTo(percentualMaximo) == 1) {
			throw new ActionServletException(
					"atencao.percentual_maior_percentual_maximo");
		}

	}
}
