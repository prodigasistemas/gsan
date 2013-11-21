package gcom.relatorio.cobranca.contratoparcelamento;

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * 
 * @author Hugo Azevedo
 * @date 26/05/2011
 * 
 */
public class RelatorioEmitirComprovantePagamentoContratoParcelamentoBean implements RelatorioBean {
	
	private String parcela;
	private String parcelaTotal;
	private String tipoDocumento;

	
	
	private String campoDescricaoDoc1;
	private String campoDescricaoDoc2;
	private String campoDescricaoDoc3;
	private String campoDescricaoDoc4;
	private String campoDescricaoDoc5;
	private String campoDescricaoDoc6;
	
	private String getDescricaoDoc1;
	private String getDescricaoDoc2;
	private String getDescricaoDoc3;
	private String getDescricaoDoc4;
	private String getDescricaoDoc5;
	private BigDecimal getDescricaoDoc6;
	
	
	public RelatorioEmitirComprovantePagamentoContratoParcelamentoBean(String parcela, String parcelaTotal, String tipoDocumento, String campoDescricaoDoc1, String campoDescricaoDoc2, String campoDescricaoDoc3, String campoDescricaoDoc4, String campoDescricaoDoc5, String campoDescricaoDoc6, String getDescricaoDoc1, String getDescricaoDoc2, String getDescricaoDoc3, String getDescricaoDoc4, String getDescricaoDoc5, BigDecimal getDescricaoDoc6) {
		this.parcela = parcela;
		this.parcelaTotal = parcelaTotal;
		this.tipoDocumento = tipoDocumento;
		this.campoDescricaoDoc1 = campoDescricaoDoc1;
		this.campoDescricaoDoc2 = campoDescricaoDoc2;
		this.campoDescricaoDoc3 = campoDescricaoDoc3;
		this.campoDescricaoDoc4 = campoDescricaoDoc4;
		this.campoDescricaoDoc5 = campoDescricaoDoc5;
		this.campoDescricaoDoc6 = campoDescricaoDoc6;
		this.getDescricaoDoc1 = getDescricaoDoc1;
		this.getDescricaoDoc2 = getDescricaoDoc2;
		this.getDescricaoDoc3 = getDescricaoDoc3;
		this.getDescricaoDoc4 = getDescricaoDoc4;
		this.getDescricaoDoc5 = getDescricaoDoc5;
		this.getDescricaoDoc6 = getDescricaoDoc6;
	}


	public String getCampoDescricaoDoc1() {
		return campoDescricaoDoc1;
	}


	public void setCampoDescricaoDoc1(String campoDescricaoDoc1) {
		this.campoDescricaoDoc1 = campoDescricaoDoc1;
	}


	public String getCampoDescricaoDoc2() {
		return campoDescricaoDoc2;
	}


	public void setCampoDescricaoDoc2(String campoDescricaoDoc2) {
		this.campoDescricaoDoc2 = campoDescricaoDoc2;
	}


	public String getCampoDescricaoDoc3() {
		return campoDescricaoDoc3;
	}


	public void setCampoDescricaoDoc3(String campoDescricaoDoc3) {
		this.campoDescricaoDoc3 = campoDescricaoDoc3;
	}


	public String getCampoDescricaoDoc4() {
		return campoDescricaoDoc4;
	}


	public void setCampoDescricaoDoc4(String campoDescricaoDoc4) {
		this.campoDescricaoDoc4 = campoDescricaoDoc4;
	}


	public String getCampoDescricaoDoc5() {
		return campoDescricaoDoc5;
	}


	public void setCampoDescricaoDoc5(String campoDescricaoDoc5) {
		this.campoDescricaoDoc5 = campoDescricaoDoc5;
	}


	public String getCampoDescricaoDoc6() {
		return campoDescricaoDoc6;
	}


	public void setCampoDescricaoDoc6(String campoDescricaoDoc6) {
		this.campoDescricaoDoc6 = campoDescricaoDoc6;
	}


	public String getDescricaoDoc1() {
		return getDescricaoDoc1;
	}


	public void setDescricaoDoc1(String getDescricaoDoc1) {
		this.getDescricaoDoc1 = getDescricaoDoc1;
	}


	public String getDescricaoDoc2() {
		return getDescricaoDoc2;
	}


	public void setDescricaoDoc2(String getDescricaoDoc2) {
		this.getDescricaoDoc2 = getDescricaoDoc2;
	}


	public String getDescricaoDoc3() {
		return getDescricaoDoc3;
	}


	public void setDescricaoDoc3(String getDescricaoDoc3) {
		this.getDescricaoDoc3 = getDescricaoDoc3;
	}


	public String getDescricaoDoc4() {
		return getDescricaoDoc4;
	}


	public void setDescricaoDoc4(String getDescricaoDoc4) {
		this.getDescricaoDoc4 = getDescricaoDoc4;
	}


	public String getDescricaoDoc5() {
		return getDescricaoDoc5;
	}


	public void setDescricaoDoc5(String getDescricaoDoc5) {
		this.getDescricaoDoc5 = getDescricaoDoc5;
	}


	public BigDecimal getDescricaoDoc6() {
		return getDescricaoDoc6;
	}


	public void setDescricaoDoc6(BigDecimal getDescricaoDoc6) {
		this.getDescricaoDoc6 = getDescricaoDoc6;
	}


	public String getParcela() {
		return parcela;
	}


	public void setParcela(String parcela) {
		this.parcela = parcela;
	}


	public String getParcelaTotal() {
		return parcelaTotal;
	}


	public void setParcelaTotal(String parcelaTotal) {
		this.parcelaTotal = parcelaTotal;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
	
	
	
}