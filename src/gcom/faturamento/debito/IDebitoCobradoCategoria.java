package gcom.faturamento.debito;

import gcom.cadastro.imovel.Categoria;

import java.math.BigDecimal;
import java.util.Date;

public interface IDebitoCobradoCategoria {

	public Integer getQuantidadeEconomia();
    public void setQuantidadeEconomia(Integer quantidadeEconomia);

    public Date getUltimaAlteracao();
    public void setUltimaAlteracao(Date ultimaAlteracao);

    public BigDecimal getValorCategoria();
    public void setValorCategoria(BigDecimal valorCategoria);

    public IDebitoCobrado getDebitoCobrado();
    public void setDebitoCobrado(IDebitoCobrado debitoCobrado);

    public Categoria getCategoria();
    public void setCategoria(Categoria categoria);

}
