package gcom.faturamento.conta;

import gcom.faturamento.debito.DebitoCreditoSituacao;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IConta extends Serializable {

	public void setId(Integer id);
	public void setValorAgua(BigDecimal valorAgua);
	public void setValorEsgoto(BigDecimal valorEsgoto);
	public void setDebitos(BigDecimal debitos);
	public void setValorCreditos(BigDecimal valorCreditos);
	public void setValorImposto(BigDecimal valorImposto);
	public void setReferenciaContabil(Integer referenciaContabil);
	public void setDebitoCreditoSituacaoAtual(DebitoCreditoSituacao debitoCreditoSituacaoAtual);
}
