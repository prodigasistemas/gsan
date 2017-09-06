package gcom.relatorio.faturamento.dto;

import gcom.relatorio.cliente.ReportElementType;
import gcom.relatorio.cliente.ReportItemDTO;

import java.math.BigDecimal;

public class RelatorioAgenciaReguladoraDTO implements ReportItemDTO {

	private static final long serialVersionUID = 4309079765574906490L;

	@ReportElementType(description = "Situacao", group = true, type = ReportElementType.TYPE_STRING)
	private String situacao;

	@ReportElementType(description = "Localidade", type = ReportElementType.TYPE_STRING)
	private String localidade;

	@ReportElementType(description = "Valor agua", type = ReportElementType.TYPE_MONEY, totalizer = true)
	private String valorAgua;

	@ReportElementType(description = "Valor esgoto", type = ReportElementType.TYPE_MONEY, totalizer = true)
	private String valorEsgoto;

	@ReportElementType(description = "Total", type = ReportElementType.TYPE_MONEY, totalizer = true)
	private String valorTotal;

	public RelatorioAgenciaReguladoraDTO(String localidade, BigDecimal valorAgua, BigDecimal valorEsgoto, String situacao, BigDecimal valorTotal) {
		super();
		this.localidade = localidade;
		this.valorAgua = valorAgua.toString();
		this.valorEsgoto = valorEsgoto.toString();
		this.situacao = situacao;
		this.valorTotal = valorTotal.toString();
	}

	public RelatorioAgenciaReguladoraDTO() {
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(String valorAgua) {
		this.valorAgua = valorAgua;
	}

	public String getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(String valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
}