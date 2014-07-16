package gcom.faturamento.credito;

import gcom.cadastro.imovel.Categoria;

import java.math.BigDecimal;
import java.util.Date;

public interface ICreditoRealizadoCategoria {

	public Integer getQuantidadeEconomia();
	public BigDecimal getValorCategoria();
	public Date getUltimaAlteracao();
	public ICreditoRealizado getCreditoRealizado();
	public Categoria getCategoria();

	public void setQuantidadeEconomia(Integer quantidadeEconomia);
	public void setValorCategoria(BigDecimal valorCategoria);
	public void setUltimaAlteracao(Date ultimaAlteracao);
	public void setCreditoRealizado(ICreditoRealizado creditoRealizado);
	public void setCategoria(Categoria categoria);

}
