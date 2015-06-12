package gcom.relatorio.arrecadacao.dto;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import br.com.prodigasistemas.gsan.relatorio.ReportElementType;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class ResumoCreditosAvisosBancariosDTO implements ReportItemDTO {

	private static final long serialVersionUID = 7599238519918851586L;
	
	@ReportElementType(description="Data do Crédito Previsto", group=true)
	private String dataPagamentoPrevisto;
	
	@ReportElementType(description="Data do Aviso")
	private String dataRealizada;
	
	@ReportElementType(description="Arrecadador")
	private String descricaoArrecadador;
	
	@ReportElementType(description="Valor do Pagamento (R$)")
	private String valorPagamento;

	public ResumoCreditosAvisosBancariosDTO() {}
	
	public ResumoCreditosAvisosBancariosDTO(Date dataPagamentoPrevisto, Date dataRealizada, String descricaoArrecadador, BigDecimal valorPagamento) {
		super();
		this.dataPagamentoPrevisto = Util.formatarData(dataPagamentoPrevisto);
		this.dataRealizada = Util.formatarData(dataRealizada);
		this.descricaoArrecadador = descricaoArrecadador;
		this.valorPagamento = Util.formatarMoedaReal(valorPagamento);
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
