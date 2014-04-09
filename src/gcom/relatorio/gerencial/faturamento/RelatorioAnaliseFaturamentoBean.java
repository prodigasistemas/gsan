package gcom.relatorio.gerencial.faturamento;

import java.math.BigDecimal;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioAnaliseFaturamentoBean implements RelatorioBean {

	private String descricao;

	private Integer quantidadeConta;
	
	private Integer quantidadeEconomia;
	
	private Integer volumeConsumidoAgua;
	
	private BigDecimal valorFaturadoAgua;
	
	private Integer volumeColetadoEsgoto;
	
	private BigDecimal valorFaturadoEsgoto;
	
	private BigDecimal debitosCobrados;
	
	private BigDecimal creditosRealizados;
	
	private BigDecimal totalCobrado;
	
	private BigDecimal valorImpostos;
	
	private String idQuebra;
	
	private String idAgrupamento;
	
	private JRBeanCollectionDataSource arrayDetalhesJRDetail;
	

	public RelatorioAnaliseFaturamentoBean(String descricao,
			Integer quantidadeConta, Integer quantidadeEconomia,
			Integer volumeConsumidoAgua, BigDecimal valorFaturadoAgua,
			Integer volumeColetadoEsgoto, BigDecimal valorFaturadoEsgoto,
			BigDecimal debitosCobrados, BigDecimal creditosRealizados,
			BigDecimal totalCobrado) {
		this.descricao = descricao;
		this.quantidadeConta = quantidadeConta;
		this.quantidadeEconomia = quantidadeEconomia;
		this.volumeConsumidoAgua = volumeConsumidoAgua;
		this.valorFaturadoAgua = valorFaturadoAgua;
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
		this.debitosCobrados = debitosCobrados;
		this.creditosRealizados = creditosRealizados;
		this.totalCobrado = totalCobrado;
	}

	public RelatorioAnaliseFaturamentoBean(String descricao,
			Integer quantidadeConta, Integer quantidadeEconomia,
			Integer volumeConsumidoAgua, BigDecimal valorFaturadoAgua,
			Integer volumeColetadoEsgoto, BigDecimal valorFaturadoEsgoto,
			BigDecimal debitosCobrados, BigDecimal creditosRealizados,
			BigDecimal totalCobrado, BigDecimal valorImpostos, String idQuebra,
			String idAgrupamento) {
		this.descricao = descricao;
		this.quantidadeConta = quantidadeConta;
		this.quantidadeEconomia = quantidadeEconomia;
		this.volumeConsumidoAgua = volumeConsumidoAgua;
		this.valorFaturadoAgua = valorFaturadoAgua;
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
		this.debitosCobrados = debitosCobrados;
		this.creditosRealizados = creditosRealizados;
		this.totalCobrado = totalCobrado;
		this.valorImpostos = valorImpostos;
		this.idAgrupamento = idAgrupamento;
		this.idQuebra = idQuebra;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidadeConta() {
		return quantidadeConta;
	}

	public void setQuantidadeConta(Integer quantidadeConta) {
		this.quantidadeConta = quantidadeConta;
	}

	public Integer getQuantidadeEconomia() {
		return quantidadeEconomia;
	}

	public void setQuantidadeEconomia(Integer quantidadeEconomia) {
		this.quantidadeEconomia = quantidadeEconomia;
	}

	public Integer getVolumeConsumidoAgua() {
		return volumeConsumidoAgua;
	}

	public void setVolumeConsumidoAgua(Integer volumeConsumidoAgua) {
		this.volumeConsumidoAgua = volumeConsumidoAgua;
	}

	public BigDecimal getValorFaturadoAgua() {
		return valorFaturadoAgua;
	}

	public void setValorFaturadoAgua(BigDecimal valorFaturadoAgua) {
		this.valorFaturadoAgua = valorFaturadoAgua;
	}


	public BigDecimal getValorFaturadoEsgoto() {
		return valorFaturadoEsgoto;
	}

	public void setValorFaturadoEsgoto(BigDecimal valorFaturadoEsgoto) {
		this.valorFaturadoEsgoto = valorFaturadoEsgoto;
	}

	public BigDecimal getDebitosCobrados() {
		return debitosCobrados;
	}

	public void setDebitosCobrados(BigDecimal debitosCobrados) {
		this.debitosCobrados = debitosCobrados;
	}

	public BigDecimal getCreditosRealizados() {
		return creditosRealizados;
	}

	public void setCreditosRealizados(BigDecimal creditosRealizados) {
		this.creditosRealizados = creditosRealizados;
	}

	public BigDecimal getTotalCobrado() {
		return totalCobrado;
	}

	public void setTotalCobrado(BigDecimal totalCobrado) {
		this.totalCobrado = totalCobrado;
	}

	public BigDecimal getValorImpostos() {
		return valorImpostos;
	}

	public void setValorImpostos(BigDecimal valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public JRBeanCollectionDataSource getArrayDetalhesJRDetail() {
		return arrayDetalhesJRDetail;
	}

	public void setArrayDetalhesJRDetail(
			JRBeanCollectionDataSource arrayDetalhesJRDetail) {
		this.arrayDetalhesJRDetail = arrayDetalhesJRDetail;
	}

	public Integer getVolumeColetadoEsgoto() {
		return volumeColetadoEsgoto;
	}

	public void setVolumeColetadoEsgoto(Integer volumeColetadoEsgoto) {
		this.volumeColetadoEsgoto = volumeColetadoEsgoto;
	}

	public String getIdQuebra() {
		return idQuebra;
	}

	public void setIdQuebra(String idQuebra) {
		this.idQuebra = idQuebra;
	}

	public String getIdAgrupamento() {
		return idAgrupamento;
	}

	public void setIdAgrupamento(String idAgrupamento) {
		this.idAgrupamento = idAgrupamento;
	}
	
	
}
