package gcom.arrecadacao;

import gcom.util.Util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class PagamentoHelper {

	private Integer idPagamento;
	private BigDecimal valorPagamento;
	private Integer idConta;
	private Integer idDebitoACobrar;
	private Integer idGuiaPagamento;
	private Integer idTipoDocumento;
	private BigDecimal valorDocumento;
	private String dataPagamento;
	private Integer idImovel;

	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public Integer getIdDebitoACobrar() {
		return idDebitoACobrar;
	}
	public void setIdDebitoACobrar(Integer idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}
	public Integer getIdGuiaPagamento() {
		return idGuiaPagamento;
	}
	public void setIdGuiaPagamento(Integer idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public Integer getDataPagamento() throws Exception{
		if (idGuiaPagamento != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return Util.getAnoMesComoInteger(format.parse(dataPagamento));
		} else {
			return Util.converterStringParaInteger(dataPagamento);
		}
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
}
