package gcom.relatorio.arrecadacao.resumocreditosavisosbancarios;

import java.math.BigDecimal;
import java.util.Date;

import br.com.prodigasistemas.gsan.relatorio.ReportElementType;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class ResumoCreditosAvisosBancariosDTO implements ReportItemDTO {

	private static final long serialVersionUID = 7599238519918851586L;
	
	@ReportElementType(description="Data pagamento previsto", group=true)
	private Date dataPagamentoPrevisto;
	
	@ReportElementType(description="Data realizada")
	private Date dataRealizada;
	
	@ReportElementType(description="Descrição arrecadador")
	private String descricaoArrecadador;
	
	@ReportElementType(description="Valor Pagamento")
	private BigDecimal valorPagamento;

	public ResumoCreditosAvisosBancariosDTO(Date dataPagamentoPrevisto, Date dataRealizada, String descricaoArrecadador, BigDecimal valorPagamento) {
		super();
		this.dataPagamentoPrevisto = dataPagamentoPrevisto;
		this.dataRealizada = dataRealizada;
		this.descricaoArrecadador = descricaoArrecadador;
		this.valorPagamento = valorPagamento;
	}

	public Date getDataPagamentoPrevisto() {
		return dataPagamentoPrevisto;
	}

	public void setDataPagamentoPrevisto(Date dataPagamentoPrevisto) {
		this.dataPagamentoPrevisto = dataPagamentoPrevisto;
	}

	public Date getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getDescricaoArrecadador() {
		return descricaoArrecadador;
	}

	public void setDescricaoArrecadador(String descricaoArrecadador) {
		this.descricaoArrecadador = descricaoArrecadador;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
}
