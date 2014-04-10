package gcom.cadastro.imovel;

import java.util.Date;

public interface IImovelSubcategoria {
	
	public Imovel getImovel();
	
	public void setImovel(Imovel imovel);
	
	public Subcategoria getSubcategoria();

	public void setSubcategoria(Subcategoria subcategoria);
	
	public short getQuantidadeEconomias();
	
	public void setQuantidadeEconomias(short getQuantidadeEconomias);
	
	public Date getUltimaAlteracao();
	
	public void setUltimaAlteracao(Date ultimaAlteracao);

}
