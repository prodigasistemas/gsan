package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de emitir histograma de agua
 *
 * @author Rafael Pinto
 * @date 01/06/2007
 */
public class EmitirHistogramaAguaHelper {
	
	private String opcaoTotalizacao = null;
	private String descricaoTitulo = null;
	
	private int totalQuantidadeLigacoes;
	private double totalPercentualParcialLigacao;
	private double totalPercentualAcumuladoLigacao;
	
	//Economias
	private int totalQuantidadeEconomias;
	
	//Consumo
	private int totalQuantidadeVolumeMedido;
	private int totalQuantidadeVolumeEstimado;
	private int totalQuantidadeVolumeTotal;
	private double totalPercentualParcialConsumo;
	private double totalPercentualAcumuladoConsumo;
	private int totalMediaConsumo;
	
	//Faturamento
	private BigDecimal totalValorFaturado;
	private double totalPercentualParcialFaturamento;
	private double totalPercentualAcumuladoFaturamento;
	
	private Collection<EmitirHistogramaAguaDetalheHelper> colecaoEmitirHistogramaAguaDetalhe = null;
	
	
	public Collection<EmitirHistogramaAguaDetalheHelper> getColecaoEmitirHistogramaAguaDetalhe() {
		return colecaoEmitirHistogramaAguaDetalhe;
	}

	public void setColecaoEmitirHistogramaAguaDetalhe(
			Collection<EmitirHistogramaAguaDetalheHelper> colecaoEmitirHistogramaAguaDetalhe) {
		this.colecaoEmitirHistogramaAguaDetalhe = colecaoEmitirHistogramaAguaDetalhe;
	}

	public EmitirHistogramaAguaHelper() { }
	
	public String getDescricaoTitulo() {
		return descricaoTitulo;
	}
	
	public void setDescricaoTitulo(String descricaoTitulo) {
		this.descricaoTitulo = descricaoTitulo;
	}
	
	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}
	
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	public int getTotalMediaConsumo() {
		
		int media = this.totalQuantidadeVolumeTotal / this.totalQuantidadeLigacoes;
		totalMediaConsumo = media;
		
		return totalMediaConsumo;
	}
	

	public void setTotalMediaConsumo(int totalMediaConsumo) {
		this.totalMediaConsumo = totalMediaConsumo;
	}

	public double getTotalPercentualAcumuladoConsumo() {
		return totalPercentualAcumuladoConsumo;
	}

	public void setTotalPercentualAcumuladoConsumo(
			double totalPercentualAcumuladoConsumo) {
		this.totalPercentualAcumuladoConsumo = totalPercentualAcumuladoConsumo;
	}

	public double getTotalPercentualAcumuladoFaturamento() {
		return totalPercentualAcumuladoFaturamento;
	}

	public void setTotalPercentualAcumuladoFaturamento(
			double totalPercentualAcumuladoFaturamento) {
		this.totalPercentualAcumuladoFaturamento = totalPercentualAcumuladoFaturamento;
	}

	public double getTotalPercentualAcumuladoLigacao() {
		return totalPercentualAcumuladoLigacao;
	}

	public void setTotalPercentualAcumuladoLigacao(
			double totalPercentualAcumuladoLigacao) {
		this.totalPercentualAcumuladoLigacao = totalPercentualAcumuladoLigacao;
	}

	public double getTotalPercentualParcialConsumo() {
		return totalPercentualParcialConsumo;
	}

	public void setTotalPercentualParcialConsumo(
			double totalPercentualParcialConsumo) {
		this.totalPercentualParcialConsumo = totalPercentualParcialConsumo;
	}

	public double getTotalPercentualParcialFaturamento() {
		return totalPercentualParcialFaturamento;
	}

	public void setTotalPercentualParcialFaturamento(
			double totalPercentualParcialFaturamento) {
		this.totalPercentualParcialFaturamento = totalPercentualParcialFaturamento;
	}

	public double getTotalPercentualParcialLigacao() {
		return totalPercentualParcialLigacao;
	}

	public void setTotalPercentualParcialLigacao(
			double totalPercentualParcialLigacao) {
		this.totalPercentualParcialLigacao = totalPercentualParcialLigacao;
	}

	public int getTotalQuantidadeEconomias() {
		return totalQuantidadeEconomias;
	}

	public void setTotalQuantidadeEconomias(int totalQuantidadeEconomias) {
		this.totalQuantidadeEconomias = totalQuantidadeEconomias;
	}

	public int getTotalQuantidadeLigacoes() {
		return totalQuantidadeLigacoes;
	}

	public void setTotalQuantidadeLigacoes(int totalQuantidadeLigacoes) {
		this.totalQuantidadeLigacoes = totalQuantidadeLigacoes;
	}

	public int getTotalQuantidadeVolumeEstimado() {
		return totalQuantidadeVolumeEstimado;
	}

	public void setTotalQuantidadeVolumeEstimado(int totalQuantidadeVolumeEstimado) {
		this.totalQuantidadeVolumeEstimado = totalQuantidadeVolumeEstimado;
	}

	public int getTotalQuantidadeVolumeMedido() {
		return totalQuantidadeVolumeMedido;
	}

	public void setTotalQuantidadeVolumeMedido(int totalQuantidadeVolumeMedido) {
		this.totalQuantidadeVolumeMedido = totalQuantidadeVolumeMedido;
	}

	
	public int getTotalQuantidadeVolumeTotal() {
		int total = 
			this.totalQuantidadeVolumeMedido + this.totalQuantidadeVolumeEstimado ;
		
		totalQuantidadeVolumeTotal = total;
		
		return totalQuantidadeVolumeTotal;
	}

	public BigDecimal getTotalValorFaturado() {
		return totalValorFaturado;
	}

	public void setTotalValorFaturado(BigDecimal totalValorFaturado) {
		this.totalValorFaturado = totalValorFaturado;
	}
	

	
	

}
