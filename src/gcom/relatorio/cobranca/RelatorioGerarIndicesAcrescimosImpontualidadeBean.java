package gcom.relatorio.cobranca;

import java.math.BigDecimal;

import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * [UC ]
 * 
 * @author Sávio Luiz
 * @date 27/08/2007
 */
public class RelatorioGerarIndicesAcrescimosImpontualidadeBean implements
		RelatorioBean {

	private String mesAnoReferencia;

	private String percentualMulta;

	private String percentualJurosMora;

	private String fatorAtualizacaoMonetaria;

	private String percentualAtualizacao;

	public RelatorioGerarIndicesAcrescimosImpontualidadeBean(
			IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade,
			BigDecimal fatorAtualizacaoMonetariaAnterior) {
		this.mesAnoReferencia = Util
				.formatarAnoMesParaMesAno(indicesAcrescimosImpontualidade
						.getAnoMesReferencia());
		this.percentualMulta = Util
				.formatarMoedaReal(indicesAcrescimosImpontualidade
						.getPercentualMulta());
		this.percentualJurosMora = Util
				.formatarMoedaReal(indicesAcrescimosImpontualidade
						.getPercentualJurosMora());
		this.fatorAtualizacaoMonetaria = Util
				.formatarMoedaReal4Casas(indicesAcrescimosImpontualidade
						.getFatorAtualizacaoMonetaria());
		this.percentualAtualizacao = calcularPercentualAtualizacao(
				indicesAcrescimosImpontualidade,
				fatorAtualizacaoMonetariaAnterior);

	}

	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	public String getPercentualJurosMora() {
		return percentualJurosMora;
	}

	public void setPercentualJurosMora(String percentualJurosMora) {
		this.percentualJurosMora = percentualJurosMora;
	}

	public String getPercentualMulta() {
		return percentualMulta;
	}

	public void setPercentualMulta(String percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public String getFatorAtualizacaoMonetaria() {
		return fatorAtualizacaoMonetaria;
	}

	public void setFatorAtualizacaoMonetaria(String fatorAtualizacaoMonetaria) {
		this.fatorAtualizacaoMonetaria = fatorAtualizacaoMonetaria;
	}

	public String getPercentualAtualizacao() {
		return percentualAtualizacao;
	}

	public void setPercentualAtualizacao(String percentualAtualizacao) {
		this.percentualAtualizacao = percentualAtualizacao;
	}

	public String calcularPercentualAtualizacao(
			IndicesAcrescimosImpontualidade indicesAcrescimosImpontualidade,
			BigDecimal fatorAtualizacaoMonetariaAnterior) {
		String percentualAtualizacaoCalculado = "";

		BigDecimal fatorAtualizacaoCalculado = new BigDecimal("0.00");
		if (fatorAtualizacaoMonetariaAnterior.compareTo(BigDecimal.ZERO) != 0) {
			if (indicesAcrescimosImpontualidade.getFatorAtualizacaoMonetaria() != null) {
				fatorAtualizacaoCalculado = indicesAcrescimosImpontualidade
						.getFatorAtualizacaoMonetaria().divide(
								fatorAtualizacaoMonetariaAnterior, 4,
								BigDecimal.ROUND_HALF_UP);
			}
			fatorAtualizacaoCalculado = fatorAtualizacaoCalculado
					.subtract(new BigDecimal("1"));
			fatorAtualizacaoCalculado = fatorAtualizacaoCalculado
					.multiply(new BigDecimal("100"));
		}
		percentualAtualizacaoCalculado = Util
				.formatarMoedaReal(fatorAtualizacaoCalculado);

		return percentualAtualizacaoCalculado;

	}

}
