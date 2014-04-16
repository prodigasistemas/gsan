package gcom.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de esgoto
 *
 * @author Rafael Pinto
 * @date 05/11/2007
 */
public class EmitirHistogramaEsgotoDetalheHelper {
	
	private String descricaoCategoria = null;
	private String descricaoSubcategoria = null;
	
	//Ligações
	private int quantidadeLigacoes;
	private double percentualParcialLigacao;
	private double percentualAcumuladoLigacao;
	
	//Economias
	private int quantidadeEconomias;
	
	//Consumo
	private int quantidadeVolumeMedido;
	private int quantidadeVolumeEstimado;
	private int quantidadeVolumeTotal;
	private double percentualParcialConsumo;
	private double percentualAcumuladoConsumo;
	private int mediaConsumo;
	
	//Faturamento
	private BigDecimal valorFaturado;
	private double percentualParcialFaturamento;
	private double percentualAcumuladoFaturamento;	
	
	public EmitirHistogramaEsgotoDetalheHelper() { }

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public int getMediaConsumo() {
		
		int media = this.quantidadeVolumeTotal / this.quantidadeLigacoes;
		mediaConsumo = media;
		
		return mediaConsumo;
	}

	public void setMediaConsumo(int mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public double getPercentualAcumuladoConsumo() {
		return percentualAcumuladoConsumo;
	}

	public void setPercentualAcumuladoConsumo(double percentualAcumuladoConsumo) {
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
	}

	public double getPercentualAcumuladoFaturamento() {
		return percentualAcumuladoFaturamento;
	}

	public void setPercentualAcumuladoFaturamento(
			double percentualAcumuladoFaturamento) {
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public double getPercentualAcumuladoLigacao() {
		return percentualAcumuladoLigacao;
	}

	public void setPercentualAcumuladoLigacao(double percentualAcumuladoLigacao) {
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
	}

	public double getPercentualParcialConsumo() {
		return percentualParcialConsumo;
	}

	public void setPercentualParcialConsumo(double percentualParcialConsumo) {
		this.percentualParcialConsumo = percentualParcialConsumo;
	}

	public double getPercentualParcialFaturamento() {
		return percentualParcialFaturamento;
	}

	public void setPercentualParcialFaturamento(double percentualParcialFaturamento) {
		this.percentualParcialFaturamento = percentualParcialFaturamento;
	}

	public double getPercentualParcialLigacao() {
		return percentualParcialLigacao;
	}

	public void setPercentualParcialLigacao(double percentualParcialLigacao) {
		this.percentualParcialLigacao = percentualParcialLigacao;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	public int getQuantidadeVolumeEstimado() {
		return quantidadeVolumeEstimado;
	}

	public void setQuantidadeVolumeEstimado(int quantidadeVolumeEstimado) {
		this.quantidadeVolumeEstimado = quantidadeVolumeEstimado;
	}

	public int getQuantidadeVolumeMedido() {
		return quantidadeVolumeMedido;
	}

	public void setQuantidadeVolumeMedido(int quantidadeVolumeMedido) {
		this.quantidadeVolumeMedido = quantidadeVolumeMedido;
	}

	public int getQuantidadeVolumeTotal() {
		int total = 
			this.quantidadeVolumeMedido + this.quantidadeVolumeEstimado ;
		
		quantidadeVolumeTotal = total;
		
		return quantidadeVolumeTotal;
	}

	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}

	public void setQuantidadeVolumeTotal(int quantidadeVolumeTotal) {
		this.quantidadeVolumeTotal = quantidadeVolumeTotal;
	}
	
	

}
