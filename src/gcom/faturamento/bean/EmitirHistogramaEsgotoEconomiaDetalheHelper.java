package gcom.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada do relatório 
 * de emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */
public class EmitirHistogramaEsgotoEconomiaDetalheHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoFaixa = null;
	
	private int economiasMedido = 0;
	private BigDecimal consumoMedioMedido;
	private BigDecimal consumoExcedenteMedido;
	private int volumeConsumoFaixaMedido = 0;
	private int volumeFaturadoFaixaMedido = 0;
	private BigDecimal receitaMedido = new BigDecimal(0.0);
	
	private int economiasNaoMedido = 0;
	private BigDecimal consumoMedioNaoMedido;
	private BigDecimal consumoExcedenteNaoMedido;
	private int volumeConsumoFaixaNaoMedido = 0;
	private int volumeFaturadoFaixaNaoMedido = 0;
	private BigDecimal receitaNaoMedido = new BigDecimal(0.0);
	
	private boolean existeHistograma = true;
	
	private int ligacoesMedido = 0;
	private int ligacoesNaoMedido = 0;
	
	public EmitirHistogramaEsgotoEconomiaDetalheHelper() { }

	public BigDecimal getConsumoExcedenteMedido() {
		return consumoExcedenteMedido;
	}

	public void setConsumoExcedenteMedido(BigDecimal consumoExcedenteMedido) {
		this.consumoExcedenteMedido = consumoExcedenteMedido;
	}

	public BigDecimal getConsumoExcedenteNaoMedido() {
		return consumoExcedenteNaoMedido;
	}

	public void setConsumoExcedenteNaoMedido(BigDecimal consumoExcedenteNaoMedido) {
		this.consumoExcedenteNaoMedido = consumoExcedenteNaoMedido;
	}

	public BigDecimal getConsumoMedioMedido() {
		return consumoMedioMedido;
	}

	public void setConsumoMedioMedido(BigDecimal consumoMedioMedido) {
		this.consumoMedioMedido = consumoMedioMedido;
	}

	public BigDecimal getConsumoMedioNaoMedido() {
		return consumoMedioNaoMedido;
	}

	public void setConsumoMedioNaoMedido(BigDecimal consumoMedioNaoMedido) {
		this.consumoMedioNaoMedido = consumoMedioNaoMedido;
	}

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	public int getEconomiasMedido() {
		return economiasMedido;
	}

	public void setEconomiasMedido(int economiasMedido) {
		this.economiasMedido = economiasMedido;
	}

	public int getEconomiasNaoMedido() {
		return economiasNaoMedido;
	}

	public void setEconomiasNaoMedido(int economiasNaoMedido) {
		this.economiasNaoMedido = economiasNaoMedido;
	}

	public BigDecimal getReceitaMedido() {
		return receitaMedido;
	}

	public void setReceitaMedido(BigDecimal receitaMedido) {
		this.receitaMedido = receitaMedido;
	}

	public BigDecimal getReceitaNaoMedido() {
		return receitaNaoMedido;
	}

	public void setReceitaNaoMedido(BigDecimal receitaNaoMedido) {
		this.receitaNaoMedido = receitaNaoMedido;
	}

	public int getVolumeConsumoFaixaMedido() {
		return volumeConsumoFaixaMedido;
	}

	public void setVolumeConsumoFaixaMedido(int volumeConsumoFaixaMedido) {
		this.volumeConsumoFaixaMedido = volumeConsumoFaixaMedido;
	}

	public int getVolumeConsumoFaixaNaoMedido() {
		return volumeConsumoFaixaNaoMedido;
	}

	public void setVolumeConsumoFaixaNaoMedido(int volumeConsumoFaixaNaoMedido) {
		this.volumeConsumoFaixaNaoMedido = volumeConsumoFaixaNaoMedido;
	}

	public int getVolumeFaturadoFaixaMedido() {
		return volumeFaturadoFaixaMedido;
	}

	public void setVolumeFaturadoFaixaMedido(int volumeFaturadoFaixaMedido) {
		this.volumeFaturadoFaixaMedido = volumeFaturadoFaixaMedido;
	}

	public int getVolumeFaturadoFaixaNaoMedido() {
		return volumeFaturadoFaixaNaoMedido;
	}

	public void setVolumeFaturadoFaixaNaoMedido(int volumeFaturadoFaixaNaoMedido) {
		this.volumeFaturadoFaixaNaoMedido = volumeFaturadoFaixaNaoMedido;
	}

	public boolean isExisteHistograma() {
		return existeHistograma;
	}

	public void setExisteHistograma(boolean existeHistograma) {
		this.existeHistograma = existeHistograma;
	}

	public int getLigacoesMedido() {
		return ligacoesMedido;
	}

	public void setLigacoesMedido(int ligacoesMedido) {
		this.ligacoesMedido = ligacoesMedido;
	}

	public int getLigacoesNaoMedido() {
		return ligacoesNaoMedido;
	}

	public void setLigacoesNaoMedido(int ligacoesNaoMedido) {
		this.ligacoesNaoMedido = ligacoesNaoMedido;
	}
	
	
}
