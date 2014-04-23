package gcom.relatorio.atendimentopublico;


import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC1178] Gerar Relatório de Acompanhamento dos Boletins de Medição
 * 
 * Bean responsável por auxiliar na montagem do Relatório Acompanhamento Boletim Medição.
 * 
 * @author Diogo Peixoto
 * @since 17/06/2011
 * 
 */
public class RelatorioAcompanhamentoBoletimMedicaoBean implements RelatorioBean {

	private Integer idItemServico;
	private String descricaoServico;
	private BigDecimal quantidadeOrcada;
	private BigDecimal valorUnitario;
	private Integer quantidadeItem;
	private Integer quantidadeItemAcumulada;
	private BigDecimal valorItem;
	private String unidadeItem;
	private BigDecimal valorOrcado;
	private BigDecimal valorMedido;
	private BigDecimal valorAcumuladoPeriodo;
	
	/**
	 * Construtor para o relatório de acomapanhamento de boletim medição (Simulação)
	 * 
	 * @param idItem
	 * @param descricaoServico
	 * @param qtdeOrcada
	 * @param valorUnitario
	 * @param quantidadeItem
	 * @param valorItem
	 * @param unidadeItem
	 * @param valorOrcado
	 * @param valorMedido
	 */
	public RelatorioAcompanhamentoBoletimMedicaoBean(Integer idItem, String descricaoServico, BigDecimal qtdeOrcada,
			BigDecimal valorUnitario, Integer quantidadeItem, BigDecimal valorItem, String unidadeItem, BigDecimal valorOrcado, 
			BigDecimal valorMedido, Integer quantidadeItemAcumulada, BigDecimal valorAcumuladoPeriodo){
		this.idItemServico = idItem;
		this.descricaoServico = descricaoServico;
		this.quantidadeOrcada = qtdeOrcada;
		this.valorUnitario = valorUnitario;
		this.quantidadeItem = quantidadeItem;
		this.valorItem = valorItem;
		this.unidadeItem = unidadeItem;
		this.valorOrcado = valorOrcado;
		this.valorMedido = valorMedido;
		this.quantidadeItemAcumulada = quantidadeItemAcumulada;
		this.valorAcumuladoPeriodo = valorAcumuladoPeriodo;
	}

	public Integer getIdItemServico() {
		return idItemServico;
	}

	public void setIdItemServico(Integer idItemServico) {
		this.idItemServico = idItemServico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public BigDecimal getQuantidadeOrcada() {
		return quantidadeOrcada;
	}

	public void setQuantidadeOrcada(BigDecimal quantidadeOrcada) {
		this.quantidadeOrcada = quantidadeOrcada;
	}
	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getUnidadeItem() {
		return unidadeItem;
	}

	public void setUnidadeItem(String unidadeItem) {
		this.unidadeItem = unidadeItem;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public BigDecimal getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(BigDecimal valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public BigDecimal getValorMedido() {
		return valorMedido;
	}

	public void setValorMedido(BigDecimal valorMedido) {
		this.valorMedido = valorMedido;
	}

	public Integer getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(Integer quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public Integer getQuantidadeItemAcumulada() {
		return quantidadeItemAcumulada;
	}

	public void setQuantidadeItemAcumulada(Integer quantidadeItemAcumulada) {
		this.quantidadeItemAcumulada = quantidadeItemAcumulada;
	}

	public BigDecimal getValorAcumuladoPeriodo() {
		return valorAcumuladoPeriodo;
	}

	public void setValorAcumuladoPeriodo(BigDecimal valorAcumuladoPeriodo) {
		this.valorAcumuladoPeriodo = valorAcumuladoPeriodo;
	}
}
