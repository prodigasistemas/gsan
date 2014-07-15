package gcom.faturamento.conta;

import gcom.faturamento.ImpostoTipo;

import java.math.BigDecimal;
import java.util.Date;

public interface IContaImpostosDeduzidos {

	public Integer getId();
    public BigDecimal getValorBaseCalculo();
    public Date getUltimaAlteracao();
    public IConta getConta();
	public ImpostoTipo getImpostoTipo();
	public BigDecimal getPercentualAliquota();
	public BigDecimal getValorImposto();
	
	public void setId(Integer id);
	public void setValorBaseCalculo(BigDecimal valorBaseCalculo);
	public void setUltimaAlteracao(Date ultimaAlteracao);
	public void setConta(IConta conta);
	public void setImpostoTipo(ImpostoTipo impostoTipo);
	public void setPercentualAliquota(BigDecimal percentualAliquota);
	public void setValorImposto(BigDecimal valorImposto);
}
