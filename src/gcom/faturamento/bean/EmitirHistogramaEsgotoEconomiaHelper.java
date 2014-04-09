package gcom.faturamento.bean;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Classe que irá auxiliar no formato de entrada do relatório 
 * de emitir histograma de esgoto por economia
 *
 * @author Rafael Pinto
 * 
 * @date 07/11/2007
 */
public class EmitirHistogramaEsgotoEconomiaHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String descricaoTarifa = null;
	private String descricaoPercentual = null;
	private String opcaoTotalizacao = null;
	private String descricaoFaixa = null;
	private String descricaoCategoria = null;
	
	//Parte com Hidrometro
	private Integer totalEconomiasMedido = null;
	private Integer totalVolumeConsumoMedido = null;
	private Integer totalVolumeFaturadoMedido = null; 
	private BigDecimal totalReceitaMedido = null;
	private Integer totalLigacoesMedido = null;
	
	//Parte sem Hidrometro
	private Integer totalEconomiasNaoMedido = null;
	private Integer totalVolumeConsumoNaoMedido = null;
	private Integer totalVolumeFaturadoNaoMedido = null; 
	private BigDecimal totalReceitaNaoMedido = null;
	private Integer totalLigacoesNaoMedido  = null;
	
	private Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe = null;

	public EmitirHistogramaEsgotoEconomiaHelper() { }

	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	public Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> getColecaoEmitirHistogramaEsgotoEconomiaDetalhe() {
		return colecaoEmitirHistogramaEsgotoEconomiaDetalhe;
	}

	public void setColecaoEmitirHistogramaEsgotoEconomiaDetalhe(
			Collection<EmitirHistogramaEsgotoEconomiaDetalheHelper> colecaoEmitirHistogramaEsgotoEconomiaDetalhe) {
		this.colecaoEmitirHistogramaEsgotoEconomiaDetalhe = colecaoEmitirHistogramaEsgotoEconomiaDetalhe;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public Integer getTotalEconomiasMedido() {
		return totalEconomiasMedido;
	}

	public void setTotalEconomiasMedido(Integer totalEconomiasMedido) {
		this.totalEconomiasMedido = totalEconomiasMedido;
	}

	public Integer getTotalEconomiasNaoMedido() {
		return totalEconomiasNaoMedido;
	}

	public void setTotalEconomiasNaoMedido(Integer totalEconomiasNaoMedido) {
		this.totalEconomiasNaoMedido = totalEconomiasNaoMedido;
	}

	public BigDecimal getTotalReceitaMedido() {
		return totalReceitaMedido;
	}

	public void setTotalReceitaMedido(BigDecimal totalReceitaMedido) {
		this.totalReceitaMedido = totalReceitaMedido;
	}

	public BigDecimal getTotalReceitaNaoMedido() {
		return totalReceitaNaoMedido;
	}

	public void setTotalReceitaNaoMedido(BigDecimal totalReceitaNaoMedido) {
		this.totalReceitaNaoMedido = totalReceitaNaoMedido;
	}

	public Integer getTotalVolumeConsumoMedido() {
		return totalVolumeConsumoMedido;
	}

	public void setTotalVolumeConsumoMedido(Integer totalVolumeConsumoMedido) {
		this.totalVolumeConsumoMedido = totalVolumeConsumoMedido;
	}

	public int getTotalVolumeConsumoNaoMedido() {
		return totalVolumeConsumoNaoMedido;
	}

	public void setTotalVolumeConsumoNaoMedido(Integer totalVolumeConsumoNaoMedido) {
		this.totalVolumeConsumoNaoMedido = totalVolumeConsumoNaoMedido;
	}

	public int getTotalVolumeFaturadoMedido() {
		return totalVolumeFaturadoMedido;
	}

	public void setTotalVolumeFaturadoMedido(Integer totalVolumeFaturadoMedido) {
		this.totalVolumeFaturadoMedido = totalVolumeFaturadoMedido;
	}

	public Integer getTotalVolumeFaturadoNaoMedido() {
		return totalVolumeFaturadoNaoMedido;
	}

	public void setTotalVolumeFaturadoNaoMedido(Integer totalVolumeFaturadoNaoMedido) {
		this.totalVolumeFaturadoNaoMedido = totalVolumeFaturadoNaoMedido;
	}

	public String getDescricaoPercentual() {
		return descricaoPercentual;
	}

	public void setDescricaoPercentual(String descricaoPercentual) {
		this.descricaoPercentual = descricaoPercentual;
	}

	public String getDescricaoTarifa() {
		return descricaoTarifa;
	}

	public void setDescricaoTarifa(String descricaoTarifa) {
		this.descricaoTarifa = descricaoTarifa;
	}

	public Integer getTotalLigacoesMedido() {
		return totalLigacoesMedido;
	}

	public void setTotalLigacoesMedido(Integer totalLigacoesMedido) {
		this.totalLigacoesMedido = totalLigacoesMedido;
	}

	public Integer getTotalLigacoesNaoMedido() {
		return totalLigacoesNaoMedido;
	}

	public void setTotalLigacoesNaoMedido(Integer totalLigacoesNaoMedido) {
		this.totalLigacoesNaoMedido = totalLigacoesNaoMedido;
	}


	
	
}
