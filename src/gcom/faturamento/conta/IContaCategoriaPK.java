package gcom.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;

public interface IContaCategoriaPK {

	public Integer getIdConta();
    public void setIdConta(Integer id);

    public IConta getConta();
    public void setConta(IConta conta);
    
    public Categoria getCategoria();
    public void setCategoria(Categoria categoria);

    public Subcategoria getSubcategoria();
    public void setSubcategoria(Subcategoria subcategoria);
    

}
