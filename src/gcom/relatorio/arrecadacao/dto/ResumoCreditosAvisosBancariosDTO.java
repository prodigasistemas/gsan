package gcom.relatorio.arrecadacao.dto;

import java.math.BigDecimal;
import java.util.Date;

import gcom.relatorio.cliente.ReportElementType;
import gcom.relatorio.cliente.ReportItemDTO;
import gcom.util.FormatoData;
import gcom.util.Util;

public class ResumoCreditosAvisosBancariosDTO implements ReportItemDTO {

	private static final long serialVersionUID = 7599238519918851586L;
	
	@ReportElementType(description="Data do Credito Previsto", group=true, type=ReportElementType.TYPE_DATE)
	private String dataPagamentoPrevisto;
	
	@ReportElementType(description="Data do Aviso", type=ReportElementType.TYPE_DATE)
	private String dataRealizada;
	
	@ReportElementType(description="Arrecadador")
	private String descricaoArrecadador;
	
	@ReportElementType(description="Valor do Pagamento (R$)", align="right", totalizer=true, type=ReportElementType.TYPE_MONEY)
	private String valorPagamento;

	public ResumoCreditosAvisosBancariosDTO() {}
	
	public ResumoCreditosAvisosBancariosDTO(Date dataPagamentoPrevisto, Date dataRealizada, String descricaoArrecadador, BigDecimal valorPagamento) {
		super();
		this.dataPagamentoPrevisto = Util.formatarData(dataPagamentoPrevisto, FormatoData.AMERICANO_COM_TRACO);
		this.dataRealizada = Util.formatarData(dataRealizada, FormatoData.AMERICANO_COM_TRACO);
		this.descricaoArrecadador = descricaoArrecadador;
		this.valorPagamento = Util.converterDecimalParaString(valorPagamento);
	}

	public String getDataPagamentoPrevisto() {
		return dataPagamentoPrevisto;
	}

	public void setDataPagamentoPrevisto(String dataPagamentoPrevisto) {
		this.dataPagamentoPrevisto = dataPagamentoPrevisto;
	}

	public String getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(String dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	public String getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
}
