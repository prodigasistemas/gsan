package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

import java.math.BigDecimal;
import java.util.Date;

public interface IContaCategoriaConsumoFaixa {

	public Integer getId();
	public BigDecimal getValorAgua();
	public Integer getConsumoAgua();
	public BigDecimal getValorEsgoto();
	public Integer getConsumoEsgoto();
	public Date getUltimaAlteracao();
	public Integer getConsumoFaixaInicio();
	public Integer getConsumoFaixaFim();
	public BigDecimal getValorTarifaFaixa();
	public Categoria getCategoria();
	public IContaCategoria getContaCategoria();
	public Subcategoria getSubcategoria();
    
	public void setId(Integer id);
    public void setValorAgua(BigDecimal valorAgua);
    public void setConsumoAgua(Integer consumoAgua);
    public void setValorEsgoto(BigDecimal valorEsgoto);
    public void setConsumoEsgoto(Integer consumoEsgoto);
    public void setUltimaAlteracao(Date ultimaAlteracao);
    public void setConsumoFaixaInicio(Integer consumoFaixaInicio);
    public void setConsumoFaixaFim(Integer consumoFaixaFim);
    public void setValorTarifaFaixa(BigDecimal valorTarifaFaixa);
    public void setCategoria(Categoria categoria);
    public void setContaCategoria(IContaCategoria contaCategoria);
	public void setSubcategoria(Subcategoria subcategoria);
}
