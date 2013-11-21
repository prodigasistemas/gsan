package gcom.gui.cobranca.contratoparcelamento;

import java.math.BigDecimal;

public class DebitosClienteHelper {
	
	private Integer tipoDocumento;
	
	private Integer identificadorItem;
	
	BigDecimal valorItem;
	
	BigDecimal valorAcrescImpont;

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getIdentificadorItem() {
		return identificadorItem;
	}

	public void setIdentificadorItem(Integer identificadorItem) {
		this.identificadorItem = identificadorItem;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public BigDecimal getValorAcrescImpont() {
		return valorAcrescImpont;
	}

	public void setValorAcrescImpont(BigDecimal valorAcrescImpont) {
		this.valorAcrescImpont = valorAcrescImpont;
	}
	
	

}
