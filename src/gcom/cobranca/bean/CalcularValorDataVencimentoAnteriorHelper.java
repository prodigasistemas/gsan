package gcom.cobranca.bean;

import gcom.cobranca.CobrancaDocumentoItem;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Classe que irá auxiliar no formato do retorno do valor e da data de vencimento
 * anterior
 *
 * @author Raphael Rossiter
 * @date 30/05/2006
 */
public class CalcularValorDataVencimentoAnteriorHelper {

	private BigDecimal valorAnterior;
	private Date dataVencimentoAnterior;
	private CobrancaDocumentoItem cobrancaDocumentoItemNaoAnterior;
	private BigDecimal valorAcrescimosAnterior;
	
	public CalcularValorDataVencimentoAnteriorHelper(){}
	
	public Date getDataVencimentoAnterior() {
		return dataVencimentoAnterior;
	}
	public void setDataVencimentoAnterior(Date dataVencimentoAnterior) {
		this.dataVencimentoAnterior = dataVencimentoAnterior;
	}
	public CobrancaDocumentoItem getCobrancaDocumentoItemNaoAnterior() {
		return cobrancaDocumentoItemNaoAnterior;
	}

	public void setCobrancaDocumentoItemNaoAnterior(
			CobrancaDocumentoItem cobrancaDocumentoItemNaoAnterior) {
		this.cobrancaDocumentoItemNaoAnterior = cobrancaDocumentoItemNaoAnterior;
	}

	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}
	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public BigDecimal getValorAcrescimosAnterior() {
		return valorAcrescimosAnterior;
	}

	public void setValorAcrescimosAnterior(BigDecimal valorAcrescimosAnterior) {
		this.valorAcrescimosAnterior = valorAcrescimosAnterior;
	}
	
	
}
