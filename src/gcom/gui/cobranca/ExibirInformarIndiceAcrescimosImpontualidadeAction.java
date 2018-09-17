package gcom.gui.cobranca;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarIndiceAcrescimosImpontualidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		IndiceAcrescimosImpontualidadeForm form = (IndiceAcrescimosImpontualidadeForm) actionForm;
		if (request.getParameter("menu") != null && !request.getParameter("menu").equals("")) {
			form.setMesAnoReferencia("");
			form.setPercentualMulta("");
			form.setPercentualJurosMora("");
			form.setPercentualAtualizacaoMonetaria("");
			form.setFatorCorrecao("");
			form.setCamposDesabilitados("");
			form.setIndicadorJurosMensal(ConstantesSistema.SIM.toString());
			form.setIndicadorMultaMensal(ConstantesSistema.SIM.toString());
			form.setPercentualLimiteJuros("");
			form.setPercentualLimiteMulta("");
		}

		if (request.getParameter("calcular") != null && request.getParameter("calcular").equals("sim")) {
			calcularFatorAtualizacaoMonetaria(form, request);
		} else {
			pesquisarEnter(form, request);
		}

		return mapping.findForward("inserirAcaoCobranca");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pesquisarEnter(IndiceAcrescimosImpontualidadeForm form, HttpServletRequest request) {
		if (form.getMesAnoReferencia() != null && !form.getMesAnoReferencia().equals("")) {

			FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
			Integer anoMesReferencia = null;
			try {
				anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());

				// [FS0002] - Validar mês e ano de referência
				Integer anoMesReferenciaMaximo = getFachada().pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade();
				Integer anoMesReferenciaMaisUm = null;
				if (anoMesReferenciaMaximo != null) {
					anoMesReferenciaMaisUm = Util.somaMesAnoMesReferencia(anoMesReferenciaMaximo, 1);
				}
				if (anoMesReferenciaMaisUm != null && anoMesReferencia > anoMesReferenciaMaisUm) {
					throw new ActionServletException("atencao.mes_ano_referencia_maior", null, "" + anoMesReferenciaMaisUm);
				}
				filtroIndicesAcrescimosImpontualidade.adicionarParametro(new ParametroSimples(FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA, anoMesReferencia));
			} catch (NumberFormatException ex) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Mes/Ano Referência");
			}

			Collection pesquisa = getFachada().pesquisar(filtroIndicesAcrescimosImpontualidade, IndicesAcrescimosImpontualidade.class.getName());

			if (pesquisa != null && !pesquisa.isEmpty()) {
				IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade = (IndicesAcrescimosImpontualidade) Util.retonarObjetoDeColecao(pesquisa);
				form.setPercentualMulta(indicesAcrescimosImpontualidade.getPercentualMulta() + "");
				form.setPercentualJurosMora(indicesAcrescimosImpontualidade.getPercentualJurosMora() + "");
				form.setFatorCorrecao(indicesAcrescimosImpontualidade.getFatorAtualizacaoMonetaria() + "");
				form.setPercentualAtualizacaoMonetaria("");
				request.setAttribute("nomeCampo", "percentualAtualizacaoMonetaria");
				form.setPercentualLimiteJuros(Util.formatarMoedaReal(indicesAcrescimosImpontualidade.getPercentualLimiteJuros()));
				form.setPercentualLimiteMulta(Util.formatarMoedaReal(indicesAcrescimosImpontualidade.getPercentualLimiteMulta()));
				form.setIndicadorJurosMensal(indicesAcrescimosImpontualidade.getIndicadorJurosMensal().toString());
				form.setIndicadorMultaMensal(indicesAcrescimosImpontualidade.getIndicadorMultaMensal().toString());

				SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();

				if (anoMesReferencia <= sistemaParametro.getAnoMesArrecadacao()) {
					form.setCamposDesabilitados("sim");
				}
			} else {
				request.setAttribute("nomeCampo", "percentualMulta");
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void calcularFatorAtualizacaoMonetaria(IndiceAcrescimosImpontualidadeForm form, HttpServletRequest request) {
		if (form.getMesAnoReferencia() != null && !form.getMesAnoReferencia().equals("")) {

			if (form.getPercentualAtualizacaoMonetaria() != null) {
				BigDecimal percentual = Util.formatarMoedaRealparaBigDecimal(form.getPercentualAtualizacaoMonetaria());
				
				if (percentual.compareTo(new BigDecimal("0.00")) < 0 || percentual.compareTo(new BigDecimal("100.00")) > 0) {
//				if (percentual.compareTo(new BigDecimal("0.00")) == 0 || percentual.compareTo(new BigDecimal("100.00")) > 0) {
					throw new ActionServletException("atencao.percentual_invalido", null, "Fator Atualização Monetária");
				}
			}

			FiltroIndicesAcrescimosImpontualidade filtro = new FiltroIndicesAcrescimosImpontualidade();
			Integer anoMesReferencia = null;
			try {
				anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
				Integer anoMesReferenciaMenosUm = Util.subtraiAteSeisMesesAnoMesReferencia(anoMesReferencia, 1);
				filtro.adicionarParametro(new ParametroSimples(FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA, anoMesReferenciaMenosUm));
			} catch (NumberFormatException ex) {
				throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Mes/Ano Referência");
			}

			Collection colecaoAnterior = getFachada().pesquisar(filtro, IndicesAcrescimosImpontualidade.class.getName());

			if (colecaoAnterior != null && !colecaoAnterior.isEmpty()) {
				IndicesAcrescimosImpontualidade indices = (IndicesAcrescimosImpontualidade) Util.retonarObjetoDeColecao(colecaoAnterior);

				if (form.getPercentualAtualizacaoMonetaria() != null && !form.getPercentualAtualizacaoMonetaria().equals("")) {

					BigDecimal percentualAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal(form.getPercentualAtualizacaoMonetaria());
					BigDecimal fatorAtualizacao = indices.getFatorAtualizacaoMonetaria().multiply(percentualAtualizacaoMonetaria);
					BigDecimal valorPrecentual = fatorAtualizacao.divide(new BigDecimal("100"));
					valorPrecentual = valorPrecentual.add(indices.getFatorAtualizacaoMonetaria());
					valorPrecentual = valorPrecentual.setScale(4, BigDecimal.ROUND_HALF_UP);

					String valorPercentualString = "" + valorPrecentual;

					valorPercentualString = valorPercentualString.replace(".", ",");

					form.setFatorCorrecao(valorPercentualString);
					request.setAttribute("nomeCampo", "percentualLimiteJuros");
				} else {
					throw new ActionServletException("atencao.campo_texto.numero_obrigatorio", null, "Percentual Fator Atualização Monetária");
				}

			} else {
				form.setFatorCorrecao(form.getPercentualAtualizacaoMonetaria());
				request.setAttribute("nomeCampo", "percentualLimiteJuros");
			}
		} else {
			request.setAttribute("nomeCampo", "percentualAtualizacaoMonetaria");
		}

	}

}
