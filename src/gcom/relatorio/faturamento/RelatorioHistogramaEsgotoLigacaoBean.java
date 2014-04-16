package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Rafael Pinto
 * @created 05/11/2007
 */
public class RelatorioHistogramaEsgotoLigacaoBean implements RelatorioBean {

	private String opcaoTotalizacao;
	
	private String descricao;

	private String categoria;

	private String descricaoSubcategoria;

	private String numeroLigacoes;

	private String percentualParcialLigacao;

	private String percentualAcumuladoLigacao;
	
	private String economias;

	private String volumeMedido;

	private String volumeEstimado;

	private String volumeTotal;
	
	private String percentualParcialConsumo;

	private String percentualAcumuladoConsumo;
	
	private String mediaConsumo;

	private String valorFaturamento;
	
	private String percentualParcialFaturamento;

	private String percentualAcumuladoFaturamento;

	public RelatorioHistogramaEsgotoLigacaoBean(String opcaoTotalizacao, String descricao,
			String categoria, String descricaoSubcategoria, String numeroLigacoes,
			String percentualParcialLigacao, String percentualAcumuladoLigacao,
			String economias, String volumeMedido, String volumeEstimado,
			String volumeTotal, String percentualParcialConsumo,
			String percentualAcumuladoConsumo, String mediaConsumo,
			String valorFaturamento, String percentualParcialFaturamento,
			String percentualAcumuladoFaturamento) {
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.descricao = descricao;
		this.categoria = categoria;
		this.descricaoSubcategoria = descricaoSubcategoria;
		this.numeroLigacoes = numeroLigacoes;
		this.percentualParcialLigacao = percentualParcialLigacao;
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
		this.economias = economias;
		this.volumeMedido = volumeMedido;
		this.volumeEstimado = volumeEstimado;
		this.volumeTotal = volumeTotal;
		this.percentualParcialConsumo = percentualParcialConsumo;
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
		this.mediaConsumo = mediaConsumo;
		this.valorFaturamento = valorFaturamento;
		this.percentualParcialFaturamento = percentualParcialFaturamento;
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEconomias() {
		return economias;
	}

	public void setEconomias(String economias) {
		this.economias = economias;
	}

	public String getMediaConsumo() {
		return mediaConsumo;
	}

	public void setMediaConsumo(String mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public String getNumeroLigacoes() {
		return numeroLigacoes;
	}

	public void setNumeroLigacoes(String numeroLigacoes) {
		this.numeroLigacoes = numeroLigacoes;
	}

	public String getPercentualAcumuladoConsumo() {
		return percentualAcumuladoConsumo;
	}

	public void setPercentualAcumuladoConsumo(String percentualAcumuladoConsumo) {
		this.percentualAcumuladoConsumo = percentualAcumuladoConsumo;
	}

	public String getPercentualAcumuladoFaturamento() {
		return percentualAcumuladoFaturamento;
	}

	public void setPercentualAcumuladoFaturamento(
			String percentualAcumuladoFaturamento) {
		this.percentualAcumuladoFaturamento = percentualAcumuladoFaturamento;
	}

	public String getPercentualAcumuladoLigacao() {
		return percentualAcumuladoLigacao;
	}

	public void setPercentualAcumuladoLigacao(String percentualAcumuladoLigacao) {
		this.percentualAcumuladoLigacao = percentualAcumuladoLigacao;
	}

	public String getPercentualParcialConsumo() {
		return percentualParcialConsumo;
	}

	public void setPercentualParcialConsumo(String percentualParcialConsumo) {
		this.percentualParcialConsumo = percentualParcialConsumo;
	}

	public String getPercentualParcialFaturamento() {
		return percentualParcialFaturamento;
	}

	public void setPercentualParcialFaturamento(String percentualParcialFaturamento) {
		this.percentualParcialFaturamento = percentualParcialFaturamento;
	}

	public String getPercentualParcialLigacao() {
		return percentualParcialLigacao;
	}

	public void setPercentualParcialLigacao(String percentualParcialLigacao) {
		this.percentualParcialLigacao = percentualParcialLigacao;
	}

	public String getValorFaturamento() {
		return valorFaturamento;
	}

	public void setValorFaturamento(String valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}

	public String getVolumeEstimado() {
		return volumeEstimado;
	}

	public void setVolumeEstimado(String volumeEstimado) {
		this.volumeEstimado = volumeEstimado;
	}

	public String getVolumeMedido() {
		return volumeMedido;
	}

	public void setVolumeMedido(String volumeMedido) {
		this.volumeMedido = volumeMedido;
	}

	public String getVolumeTotal() {
		return volumeTotal;
	}

	public void setVolumeTotal(String volumeTotal) {
		this.volumeTotal = volumeTotal;
	}

	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}

	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}


}
