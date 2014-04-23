package gcom.gerencial.bean;

import gcom.util.Util;
import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de relatorio
 *
 * @author Bruno Barros
 * @date 21/12/2007
 */
public class QuadroMetasAcumuladoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private String opcaoTotalizacao = "";
	private String nomeGerenciaRegional;
	private String nomeUnidadeNegocio;
	private String nomeLocalidade;
	private String nomeCentroCusto;
	
	// Ligacoes Cadastradas
	private Integer quantidadeLigacoesCadastradasSubcategoria101;
	private Integer quantidadeLigacoesCadastradasSubcategoria102;
	private Integer quantidadeLigacoesCadastradasSubcategoria103;
	private Integer quantidadeLigacoesCadastradasSubcategoria200;
	private Integer quantidadeLigacoesCadastradasSubcategoria300;
	private Integer quantidadeLigacoesCadastradasSubcategoria400;
	private Integer quantidadeLigacoesCadastradasSubcategoria116;
	private Integer quantidadeLigacoesCadastradasSubcategoria115;
	private Integer quantidadeLigacoesCadastradas;
	
	// Ligacoes Cortadas
	private Integer quantidadeLigacoesCortadasSubcategoria101;
	private Integer quantidadeLigacoesCortadasSubcategoria102;
	private Integer quantidadeLigacoesCortadasSubcategoria103;
	private Integer quantidadeLigacoesCortadasSubcategoria200;
	private Integer quantidadeLigacoesCortadasSubcategoria300;
	private Integer quantidadeLigacoesCortadasSubcategoria400;
	private Integer quantidadeLigacoesCortadasSubcategoria116;
	private Integer quantidadeLigacoesCortadasSubcategoria115;
	private Integer quantidadeLigacoesCortadas;
	
	// Ligacoes Suprimidas
	private Integer quantidadeLigacoesSuprimidasSubcategoria101;
	private Integer quantidadeLigacoesSuprimidasSubcategoria102;
	private Integer quantidadeLigacoesSuprimidasSubcategoria103;
	private Integer quantidadeLigacoesSuprimidasSubcategoria200;
	private Integer quantidadeLigacoesSuprimidasSubcategoria300;
	private Integer quantidadeLigacoesSuprimidasSubcategoria400;
	private Integer quantidadeLigacoesSuprimidasSubcategoria116;
	private Integer quantidadeLigacoesSuprimidasSubcategoria115;
	private Integer quantidadeLigacoesSuprimidas;
	
	// Ligacoes Ativas
	private Integer quantidadeLigacoesAtivasSubcategoria101;
	private Integer quantidadeLigacoesAtivasSubcategoria102;
	private Integer quantidadeLigacoesAtivasSubcategoria103;
	private Integer quantidadeLigacoesAtivasSubcategoria200;
	private Integer quantidadeLigacoesAtivasSubcategoria300;
	private Integer quantidadeLigacoesAtivasSubcategoria400;
	private Integer quantidadeLigacoesAtivasSubcategoria116;
	private Integer quantidadeLigacoesAtivasSubcategoria115;
	private Integer quantidadeLigacoesAtivas;
	
	// Ligacoes Ativas com débitos a mais de 3 meses
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria101;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria102;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria103;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria200;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria300;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria400;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria116;
	private Integer quantidadeLigacoesAtivasDebitos3mSubcategoria115;
	private Integer quantidadeLigacoesAtivasDebitos3m;
	
	// Ligacoes Consumo Medido
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria101;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria102;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria103;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria200;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria300;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria400;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria116;
	private Integer quantidadeLigacoesConsumoMedidoSubcategoria115;
	private Integer quantidadeLigacoesConsumoMedido;
	
	// Ligacoes Consumo 5m3
	private Integer quantidadeLigacoesConsumo5m3Subcategoria101;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria102;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria103;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria200;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria300;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria400;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria116;
	private Integer quantidadeLigacoesConsumo5m3Subcategoria115;
	private Integer quantidadeLigacoesConsumo5m3;
	
	// Ligacoes Consumo Não Medido
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria101;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria102;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria103;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria200;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria300;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria400;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria116;
	private Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria115;
	private Integer quantidadeLigacoesConsumoNaoMedido;
	
	// Ligacoes Consumo Medio
	private Integer quantidadeLigacoesConsumoMediaSubcategoria101;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria102;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria103;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria200;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria300;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria400;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria116;
	private Integer quantidadeLigacoesConsumoMediaSubcategoria115;
	private Integer quantidadeLigacoesConsumoMedia;
	
	// Ligacoes quantidade Economias
	private Integer quantidadeEconomiasSubcategoria101;
	private Integer quantidadeEconomiasSubcategoria102;
	private Integer quantidadeEconomiasSubcategoria103;
	private Integer quantidadeEconomiasSubcategoria200;
	private Integer quantidadeEconomiasSubcategoria300;
	private Integer quantidadeEconomiasSubcategoria400;
	private Integer quantidadeEconomiasSubcategoria116;
	private Integer quantidadeEconomiasSubcategoria115;
	private Integer quantidadeEconomias;
	
	public String getNomeCentroCusto() {
	
		return nomeCentroCusto;
	}
	
	public void setNomeCentroCusto(String nomeCentroCusto) {
	
		this.nomeCentroCusto = nomeCentroCusto;
	}
	
	public String getNomeGerenciaRegional() {
	
		return nomeGerenciaRegional;
	}
	
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
	
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	
	public String getNomeLocalidade() {
	
		return nomeLocalidade;
	}
	
	public void setNomeLocalidade(String nomeLocalidade) {
	
		this.nomeLocalidade = nomeLocalidade;
	}
	
	public String getNomeUnidadeNegocio() {
	
		return nomeUnidadeNegocio;
	}
	
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
	
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	
	public String getOpcaoTotalizacao() {
	
		return opcaoTotalizacao;
	}
	
	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
	
		this.opcaoTotalizacao = opcaoTotalizacao;
	}
	
	public Integer getQuantidadeEconomias() {
	
		return quantidadeEconomias;
	}
	
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
	
		this.quantidadeEconomias = quantidadeEconomias;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria102() {
	
		return quantidadeEconomiasSubcategoria102;
	}
	
	public void setQuantidadeEconomiasSubcategoria102(
			Integer quantidadeEconomiasSubcategoria102) {
	
		this.quantidadeEconomiasSubcategoria102 = quantidadeEconomiasSubcategoria102;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria103() {
	
		return quantidadeEconomiasSubcategoria103;
	}
	
	public void setQuantidadeEconomiasSubcategoria103(
			Integer quantidadeEconomiasSubcategoria103) {
	
		this.quantidadeEconomiasSubcategoria103 = quantidadeEconomiasSubcategoria103;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria115() {
	
		return quantidadeEconomiasSubcategoria115;
	}
	
	public void setQuantidadeEconomiasSubcategoria115(
			Integer quantidadeEconomiasSubcategoria115) {
	
		this.quantidadeEconomiasSubcategoria115 = quantidadeEconomiasSubcategoria115;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria116() {
	
		return quantidadeEconomiasSubcategoria116;
	}
	
	public void setQuantidadeEconomiasSubcategoria116(
			Integer quantidadeEconomiasSubcategoria116) {
	
		this.quantidadeEconomiasSubcategoria116 = quantidadeEconomiasSubcategoria116;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria200() {
	
		return quantidadeEconomiasSubcategoria200;
	}
	
	public void setQuantidadeEconomiasSubcategoria200(
			Integer quantidadeEconomiasSubcategoria200) {
	
		this.quantidadeEconomiasSubcategoria200 = quantidadeEconomiasSubcategoria200;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria300() {
	
		return quantidadeEconomiasSubcategoria300;
	}
	
	public void setQuantidadeEconomiasSubcategoria300(
			Integer quantidadeEconomiasSubcategoria300) {
	
		this.quantidadeEconomiasSubcategoria300 = quantidadeEconomiasSubcategoria300;
	}
	
	public Integer getQuantidadeEconomiasSubcategoria400() {
	
		return quantidadeEconomiasSubcategoria400;
	}
	
	public void setQuantidadeEconomiasSubcategoria400(
			Integer quantidadeEconomiasSubcategoria400) {
	
		this.quantidadeEconomiasSubcategoria400 = quantidadeEconomiasSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesAtivas() {
	
		return quantidadeLigacoesAtivas;
	}
	
	public void setQuantidadeLigacoesAtivas(Integer quantidadeLigacoesAtivas) {
	
		this.quantidadeLigacoesAtivas = quantidadeLigacoesAtivas;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3m() {
	
		return quantidadeLigacoesAtivasDebitos3m;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3m(
			Integer quantidadeLigacoesAtivasDebitos3m) {
	
		this.quantidadeLigacoesAtivasDebitos3m = quantidadeLigacoesAtivasDebitos3m;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria101() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria101;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria101(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria101) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria101 = quantidadeLigacoesAtivasDebitos3mSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria102() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria102;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria102(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria102) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria102 = quantidadeLigacoesAtivasDebitos3mSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria103() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria103;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria103(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria103) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria103 = quantidadeLigacoesAtivasDebitos3mSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria115() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria115;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria115(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria115) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria115 = quantidadeLigacoesAtivasDebitos3mSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria116() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria116;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria116(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria116) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria116 = quantidadeLigacoesAtivasDebitos3mSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria200() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria200;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria200(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria200) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria200 = quantidadeLigacoesAtivasDebitos3mSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria300() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria300;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria300(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria300) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria300 = quantidadeLigacoesAtivasDebitos3mSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesAtivasDebitos3mSubcategoria400() {
	
		return quantidadeLigacoesAtivasDebitos3mSubcategoria400;
	}
	
	public void setQuantidadeLigacoesAtivasDebitos3mSubcategoria400(
			Integer quantidadeLigacoesAtivasDebitos3mSubcategoria400) {
	
		this.quantidadeLigacoesAtivasDebitos3mSubcategoria400 = quantidadeLigacoesAtivasDebitos3mSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria101() {
	
		return quantidadeLigacoesAtivasSubcategoria101;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria101(
			Integer quantidadeLigacoesAtivasSubcategoria101) {
	
		this.quantidadeLigacoesAtivasSubcategoria101 = quantidadeLigacoesAtivasSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria102() {
	
		return quantidadeLigacoesAtivasSubcategoria102;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria102(
			Integer quantidadeLigacoesAtivasSubcategoria102) {
	
		this.quantidadeLigacoesAtivasSubcategoria102 = quantidadeLigacoesAtivasSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria103() {
	
		return quantidadeLigacoesAtivasSubcategoria103;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria103(
			Integer quantidadeLigacoesAtivasSubcategoria103) {
	
		this.quantidadeLigacoesAtivasSubcategoria103 = quantidadeLigacoesAtivasSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria115() {
	
		return quantidadeLigacoesAtivasSubcategoria115;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria115(
			Integer quantidadeLigacoesAtivasSubcategoria115) {
	
		this.quantidadeLigacoesAtivasSubcategoria115 = quantidadeLigacoesAtivasSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria116() {
	
		return quantidadeLigacoesAtivasSubcategoria116;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria116(
			Integer quantidadeLigacoesAtivasSubcategoria116) {
	
		this.quantidadeLigacoesAtivasSubcategoria116 = quantidadeLigacoesAtivasSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria200() {
	
		return quantidadeLigacoesAtivasSubcategoria200;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria200(
			Integer quantidadeLigacoesAtivasSubcategoria200) {
	
		this.quantidadeLigacoesAtivasSubcategoria200 = quantidadeLigacoesAtivasSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria300() {
	
		return quantidadeLigacoesAtivasSubcategoria300;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria300(
			Integer quantidadeLigacoesAtivasSubcategoria300) {
	
		this.quantidadeLigacoesAtivasSubcategoria300 = quantidadeLigacoesAtivasSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesAtivasSubcategoria400() {
	
		return quantidadeLigacoesAtivasSubcategoria400;
	}
	
	public void setQuantidadeLigacoesAtivasSubcategoria400(
			Integer quantidadeLigacoesAtivasSubcategoria400) {
	
		this.quantidadeLigacoesAtivasSubcategoria400 = quantidadeLigacoesAtivasSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesCadastradas() {
	
		return quantidadeLigacoesCadastradas;
	}
	
	public void setQuantidadeLigacoesCadastradas(
			Integer quantidadeLigacoesCadastradas) {
	
		this.quantidadeLigacoesCadastradas = quantidadeLigacoesCadastradas;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria101() {
	
		return quantidadeLigacoesCadastradasSubcategoria101;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria101(
			Integer quantidadeLigacoesCadastradasSubcategoria101) {
	
		this.quantidadeLigacoesCadastradasSubcategoria101 = quantidadeLigacoesCadastradasSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria102() {
	
		return quantidadeLigacoesCadastradasSubcategoria102;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria102(
			Integer quantidadeLigacoesCadastradasSubcategoria102) {
	
		this.quantidadeLigacoesCadastradasSubcategoria102 = quantidadeLigacoesCadastradasSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria103() {
	
		return quantidadeLigacoesCadastradasSubcategoria103;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria103(
			Integer quantidadeLigacoesCadastradasSubcategoria103) {
	
		this.quantidadeLigacoesCadastradasSubcategoria103 = quantidadeLigacoesCadastradasSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria115() {
	
		return quantidadeLigacoesCadastradasSubcategoria115;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria115(
			Integer quantidadeLigacoesCadastradasSubcategoria115) {
	
		this.quantidadeLigacoesCadastradasSubcategoria115 = quantidadeLigacoesCadastradasSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria116() {
	
		return quantidadeLigacoesCadastradasSubcategoria116;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria116(
			Integer quantidadeLigacoesCadastradasSubcategoria116) {
	
		this.quantidadeLigacoesCadastradasSubcategoria116 = quantidadeLigacoesCadastradasSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria200() {
	
		return quantidadeLigacoesCadastradasSubcategoria200;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria200(
			Integer quantidadeLigacoesCadastradasSubcategoria200) {
	
		this.quantidadeLigacoesCadastradasSubcategoria200 = quantidadeLigacoesCadastradasSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria300() {
	
		return quantidadeLigacoesCadastradasSubcategoria300;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria300(
			Integer quantidadeLigacoesCadastradasSubcategoria300) {
	
		this.quantidadeLigacoesCadastradasSubcategoria300 = quantidadeLigacoesCadastradasSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesCadastradasSubcategoria400() {
	
		return quantidadeLigacoesCadastradasSubcategoria400;
	}
	
	public void setQuantidadeLigacoesCadastradasSubcategoria400(
			Integer quantidadeLigacoesCadastradasSubcategoria400) {
	
		this.quantidadeLigacoesCadastradasSubcategoria400 = quantidadeLigacoesCadastradasSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3() {
	
		return quantidadeLigacoesConsumo5m3;
	}
	
	public void setQuantidadeLigacoesConsumo5m3(Integer quantidadeLigacoesConsumo5m3) {
	
		this.quantidadeLigacoesConsumo5m3 = quantidadeLigacoesConsumo5m3;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria101() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria101;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria101(
			Integer quantidadeLigacoesConsumo5m3Subcategoria101) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria101 = quantidadeLigacoesConsumo5m3Subcategoria101;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria102() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria102;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria102(
			Integer quantidadeLigacoesConsumo5m3Subcategoria102) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria102 = quantidadeLigacoesConsumo5m3Subcategoria102;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria103() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria103;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria103(
			Integer quantidadeLigacoesConsumo5m3Subcategoria103) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria103 = quantidadeLigacoesConsumo5m3Subcategoria103;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria115() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria115;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria115(
			Integer quantidadeLigacoesConsumo5m3Subcategoria115) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria115 = quantidadeLigacoesConsumo5m3Subcategoria115;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria116() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria116;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria116(
			Integer quantidadeLigacoesConsumo5m3Subcategoria116) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria116 = quantidadeLigacoesConsumo5m3Subcategoria116;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria200() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria200;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria200(
			Integer quantidadeLigacoesConsumo5m3Subcategoria200) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria200 = quantidadeLigacoesConsumo5m3Subcategoria200;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria300() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria300;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria300(
			Integer quantidadeLigacoesConsumo5m3Subcategoria300) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria300 = quantidadeLigacoesConsumo5m3Subcategoria300;
	}
	
	public Integer getQuantidadeLigacoesConsumo5m3Subcategoria400() {
	
		return quantidadeLigacoesConsumo5m3Subcategoria400;
	}
	
	public void setQuantidadeLigacoesConsumo5m3Subcategoria400(
			Integer quantidadeLigacoesConsumo5m3Subcategoria400) {
	
		this.quantidadeLigacoesConsumo5m3Subcategoria400 = quantidadeLigacoesConsumo5m3Subcategoria400;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedia() {
	
		return quantidadeLigacoesConsumoMedia;
	}
	
	public void setQuantidadeLigacoesConsumoMedia(
			Integer quantidadeLigacoesConsumoMedia) {
	
		this.quantidadeLigacoesConsumoMedia = quantidadeLigacoesConsumoMedia;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria101() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria101;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria101(
			Integer quantidadeLigacoesConsumoMediaSubcategoria101) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria101 = quantidadeLigacoesConsumoMediaSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria102() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria102;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria102(
			Integer quantidadeLigacoesConsumoMediaSubcategoria102) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria102 = quantidadeLigacoesConsumoMediaSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria103() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria103;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria103(
			Integer quantidadeLigacoesConsumoMediaSubcategoria103) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria103 = quantidadeLigacoesConsumoMediaSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria115() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria115;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria115(
			Integer quantidadeLigacoesConsumoMediaSubcategoria115) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria115 = quantidadeLigacoesConsumoMediaSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria116() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria116;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria116(
			Integer quantidadeLigacoesConsumoMediaSubcategoria116) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria116 = quantidadeLigacoesConsumoMediaSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria200() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria200;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria200(
			Integer quantidadeLigacoesConsumoMediaSubcategoria200) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria200 = quantidadeLigacoesConsumoMediaSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria300() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria300;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria300(
			Integer quantidadeLigacoesConsumoMediaSubcategoria300) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria300 = quantidadeLigacoesConsumoMediaSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesConsumoMediaSubcategoria400() {
	
		return quantidadeLigacoesConsumoMediaSubcategoria400;
	}
	
	public void setQuantidadeLigacoesConsumoMediaSubcategoria400(
			Integer quantidadeLigacoesConsumoMediaSubcategoria400) {
	
		this.quantidadeLigacoesConsumoMediaSubcategoria400 = quantidadeLigacoesConsumoMediaSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedido() {
	
		return quantidadeLigacoesConsumoMedido;
	}
	
	public void setQuantidadeLigacoesConsumoMedido(
			Integer quantidadeLigacoesConsumoMedido) {
	
		this.quantidadeLigacoesConsumoMedido = quantidadeLigacoesConsumoMedido;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria101() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria101;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria101(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria101) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria101 = quantidadeLigacoesConsumoMedidoSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria102() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria102;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria102(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria102) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria102 = quantidadeLigacoesConsumoMedidoSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria103() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria103;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria103(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria103) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria103 = quantidadeLigacoesConsumoMedidoSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria115() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria115;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria115(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria115) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria115 = quantidadeLigacoesConsumoMedidoSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria116() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria116;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria116(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria116) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria116 = quantidadeLigacoesConsumoMedidoSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria200() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria200;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria200(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria200) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria200 = quantidadeLigacoesConsumoMedidoSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria300() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria300;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria300(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria300) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria300 = quantidadeLigacoesConsumoMedidoSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesConsumoMedidoSubcategoria400() {
	
		return quantidadeLigacoesConsumoMedidoSubcategoria400;
	}
	
	public void setQuantidadeLigacoesConsumoMedidoSubcategoria400(
			Integer quantidadeLigacoesConsumoMedidoSubcategoria400) {
	
		this.quantidadeLigacoesConsumoMedidoSubcategoria400 = quantidadeLigacoesConsumoMedidoSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedido() {
	
		return quantidadeLigacoesConsumoNaoMedido;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedido(
			Integer quantidadeLigacoesConsumoNaoMedido) {
	
		this.quantidadeLigacoesConsumoNaoMedido = quantidadeLigacoesConsumoNaoMedido;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria101() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria101;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria101(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria101) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria101 = quantidadeLigacoesConsumoNaoMedidoSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria102() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria102;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria102(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria102) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria102 = quantidadeLigacoesConsumoNaoMedidoSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria103() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria103;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria103(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria103) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria103 = quantidadeLigacoesConsumoNaoMedidoSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria115() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria115;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria115(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria115) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria115 = quantidadeLigacoesConsumoNaoMedidoSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria116() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria116;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria116(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria116) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria116 = quantidadeLigacoesConsumoNaoMedidoSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria200() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria200;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria200(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria200) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria200 = quantidadeLigacoesConsumoNaoMedidoSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria300() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria300;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria300(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria300) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria300 = quantidadeLigacoesConsumoNaoMedidoSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesConsumoNaoMedidoSubcategoria400() {
	
		return quantidadeLigacoesConsumoNaoMedidoSubcategoria400;
	}
	
	public void setQuantidadeLigacoesConsumoNaoMedidoSubcategoria400(
			Integer quantidadeLigacoesConsumoNaoMedidoSubcategoria400) {
	
		this.quantidadeLigacoesConsumoNaoMedidoSubcategoria400 = quantidadeLigacoesConsumoNaoMedidoSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesCortadas() {
	
		return quantidadeLigacoesCortadas;
	}
	
	public void setQuantidadeLigacoesCortadas(Integer quantidadeLigacoesCortadas) {
	
		this.quantidadeLigacoesCortadas = quantidadeLigacoesCortadas;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria101() {
	
		return quantidadeLigacoesCortadasSubcategoria101;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria101(
			Integer quantidadeLigacoesCortadasSubcategoria101) {
	
		this.quantidadeLigacoesCortadasSubcategoria101 = quantidadeLigacoesCortadasSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria102() {
	
		return quantidadeLigacoesCortadasSubcategoria102;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria102(
			Integer quantidadeLigacoesCortadasSubcategoria102) {
	
		this.quantidadeLigacoesCortadasSubcategoria102 = quantidadeLigacoesCortadasSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria103() {
	
		return quantidadeLigacoesCortadasSubcategoria103;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria103(
			Integer quantidadeLigacoesCortadasSubcategoria103) {
	
		this.quantidadeLigacoesCortadasSubcategoria103 = quantidadeLigacoesCortadasSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria115() {
	
		return quantidadeLigacoesCortadasSubcategoria115;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria115(
			Integer quantidadeLigacoesCortadasSubcategoria115) {
	
		this.quantidadeLigacoesCortadasSubcategoria115 = quantidadeLigacoesCortadasSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria116() {
	
		return quantidadeLigacoesCortadasSubcategoria116;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria116(
			Integer quantidadeLigacoesCortadasSubcategoria116) {
	
		this.quantidadeLigacoesCortadasSubcategoria116 = quantidadeLigacoesCortadasSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria200() {
	
		return quantidadeLigacoesCortadasSubcategoria200;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria200(
			Integer quantidadeLigacoesCortadasSubcategoria200) {
	
		this.quantidadeLigacoesCortadasSubcategoria200 = quantidadeLigacoesCortadasSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria300() {
	
		return quantidadeLigacoesCortadasSubcategoria300;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria300(
			Integer quantidadeLigacoesCortadasSubcategoria300) {
	
		this.quantidadeLigacoesCortadasSubcategoria300 = quantidadeLigacoesCortadasSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesCortadasSubcategoria400() {
	
		return quantidadeLigacoesCortadasSubcategoria400;
	}
	
	public void setQuantidadeLigacoesCortadasSubcategoria400(
			Integer quantidadeLigacoesCortadasSubcategoria400) {
	
		this.quantidadeLigacoesCortadasSubcategoria400 = quantidadeLigacoesCortadasSubcategoria400;
	}
	
	public Integer getQuantidadeLigacoesSuprimidas() {
	
		return quantidadeLigacoesSuprimidas;
	}
	
	public void setQuantidadeLigacoesSuprimidas(Integer quantidadeLigacoesSuprimidas) {
	
		this.quantidadeLigacoesSuprimidas = quantidadeLigacoesSuprimidas;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria101() {
	
		return quantidadeLigacoesSuprimidasSubcategoria101;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria101(
			Integer quantidadeLigacoesSuprimidasSubcategoria101) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria101 = quantidadeLigacoesSuprimidasSubcategoria101;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria102() {
	
		return quantidadeLigacoesSuprimidasSubcategoria102;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria102(
			Integer quantidadeLigacoesSuprimidasSubcategoria102) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria102 = quantidadeLigacoesSuprimidasSubcategoria102;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria103() {
	
		return quantidadeLigacoesSuprimidasSubcategoria103;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria103(
			Integer quantidadeLigacoesSuprimidasSubcategoria103) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria103 = quantidadeLigacoesSuprimidasSubcategoria103;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria115() {
	
		return quantidadeLigacoesSuprimidasSubcategoria115;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria115(
			Integer quantidadeLigacoesSuprimidasSubcategoria115) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria115 = quantidadeLigacoesSuprimidasSubcategoria115;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria116() {
	
		return quantidadeLigacoesSuprimidasSubcategoria116;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria116(
			Integer quantidadeLigacoesSuprimidasSubcategoria116) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria116 = quantidadeLigacoesSuprimidasSubcategoria116;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria200() {
	
		return quantidadeLigacoesSuprimidasSubcategoria200;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria200(
			Integer quantidadeLigacoesSuprimidasSubcategoria200) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria200 = quantidadeLigacoesSuprimidasSubcategoria200;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria300() {
	
		return quantidadeLigacoesSuprimidasSubcategoria300;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria300(
			Integer quantidadeLigacoesSuprimidasSubcategoria300) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria300 = quantidadeLigacoesSuprimidasSubcategoria300;
	}
	
	public Integer getQuantidadeLigacoesSuprimidasSubcategoria400() {
	
		return quantidadeLigacoesSuprimidasSubcategoria400;
	}
	
	public void setQuantidadeLigacoesSuprimidasSubcategoria400(
			Integer quantidadeLigacoesSuprimidasSubcategoria400) {
	
		this.quantidadeLigacoesSuprimidasSubcategoria400 = quantidadeLigacoesSuprimidasSubcategoria400;
	}

	
	public Integer getQuantidadeEconomiasSubcategoria101() {
	
		return quantidadeEconomiasSubcategoria101;
	}

	
	public void setQuantidadeEconomiasSubcategoria101(
			Integer quantidadeEconomiasSubcategoria101) {
	
		this.quantidadeEconomiasSubcategoria101 = quantidadeEconomiasSubcategoria101;
	}
	
	// Calculos do indice de ( Cortadas + Suprimidas ) / Cadastradas	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria101(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria101(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria101() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria102(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria102(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria102() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria103(){
		try{
		return 
			new BigDecimal ( 
					Util.somaInteiros( 
							this.getQuantidadeLigacoesCortadasSubcategoria103(), 
							this.getQuantidadeLigacoesSuprimidasSubcategoria103() ) ).
								divide( 
										new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria200(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria200(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria200() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria300(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria300(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria300() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria400(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria400(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria400() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria116(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria116(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria116() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradasSubcategoria115(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadasSubcategoria115(), 
								this.getQuantidadeLigacoesSuprimidasSubcategoria115() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceCortadaMaisSuprimidaDivididoCadastradas(){
		try{
			return 
				new BigDecimal ( 
						Util.somaInteiros( 
								this.getQuantidadeLigacoesCortadas(), 
								this.getQuantidadeLigacoesSuprimidas() ) ).
									divide( 
											new BigDecimal( this.getQuantidadeLigacoesCadastradas() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos os indices de Ativas com débitos a mais de 3 meses / ativas
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria101(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria101() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria102(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria102() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria103(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria103() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria200(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria200() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria300(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria300() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria400(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria400() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria116(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria116() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivasSubcategoria115(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3mSubcategoria115() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivasSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceAtivasDebitos3mDividoAtivas(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesAtivasDebitos3m() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesAtivas() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos o indice de consumo 5m3 / consumo média	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria101(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria101() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria102(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria102() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria103(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria103() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria200(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria200() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}

	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria300(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria300() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria400(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria400() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria116(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria116() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedidoSubcategoria115(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3Subcategoria115() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumo5m3DividoConsumoMedido(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumo5m3() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedido() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos o consumo nao medido / consumo medido ativo
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria101(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria101() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria102(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria102() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria103(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria103() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria200(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria200() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria300(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria300() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria400(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria400() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria116(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria116() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}	
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedidoSubcategoria115(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedidoSubcategoria115() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoNaoMedidoDividoConsumoMedido(){
		try{
			return 
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoNaoMedido() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedido() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	// Calculamos o indice de consumo medido / consumo medido ativo
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria101(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria101() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria102(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria102() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria103(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria103() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria200(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria200() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria300(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria300() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria400(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria400() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria116(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria116() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedidoSubcategoria115(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMediaSubcategoria115() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedidoSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceConsumoMediaDivididoConsumoMedido(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesConsumoMedia() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesConsumoMedido() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria101(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria101() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria101() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria102(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria102() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria102() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria103(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria103() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria103() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria200(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria200() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria200() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}		
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria300(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria300() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria300() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria400(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria400() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria400() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria116(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria116() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria116() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradasSubcategoria115(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidasSubcategoria115() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradasSubcategoria115() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
	
	public BigDecimal getIndiceSuprimidaCadastradas(){
		try{
			return
				new BigDecimal ( 
						this.getQuantidadeLigacoesSuprimidas() ).divide( 
							new BigDecimal( this.getQuantidadeLigacoesCadastradas() ), 4 ,BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( 100 ) );
		} catch ( ArithmeticException e ){
			return new BigDecimal( 0 );
		}			
	}	
}
