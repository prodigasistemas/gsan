package gcom.arrecadacao.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FormasArrecadacaoDadosDiariosHelper {
	
	private Integer idArrecadador;
	
	private String descricaoArrecadador;
	
	private Date data;
	
	private Integer qtdeDocumentos;
	
	private Integer qtdePagamentos;
	
	private BigDecimal debitos;
	
	private BigDecimal descontos;
	
	private BigDecimal valorArrecadado;
	
	private BigDecimal devolucoes;
	
	private BigDecimal arrecadacaoLiquida;
	
	private BigDecimal percentual;
	
	private BigDecimal percentualDoDia;

	private BigDecimal valorTotal;
	
	private BigDecimal valorTarifa;
	
	private Date dataPrevista;
	
	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQtdeDocumentos() {
		return qtdeDocumentos;
	}

	public void setQtdeDocumentos(Integer qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}

	public Integer getQtdePagamentos() {
		return qtdePagamentos;
	}

	public void setQtdePagamentos(Integer qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	}

	public BigDecimal getDebitos() {
		return debitos;
	}

	public void setDebitos(BigDecimal debitos) {
		this.debitos = debitos;
	}

	public BigDecimal getDescontos() {
		return descontos;
	}

	public void setDescontos(BigDecimal descontos) {
		this.descontos = descontos;
	}

	public BigDecimal getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(BigDecimal valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

	public BigDecimal getDevolucoes() {
		return devolucoes;
	}

	public void setDevolucoes(BigDecimal devolucoes) {
		this.devolucoes = devolucoes;
	}

	public BigDecimal getArrecadacaoLiquida() {
		return arrecadacaoLiquida;
	}

	public void setArrecadacaoLiquida(BigDecimal arrecadacaoLiquida) {
		this.arrecadacaoLiquida = arrecadacaoLiquida;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public BigDecimal getPercentualDoDia() {
		return percentualDoDia;
	}

	public void setPercentualDoDia(BigDecimal percentualDoDia) {
		this.percentualDoDia = percentualDoDia;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
}
