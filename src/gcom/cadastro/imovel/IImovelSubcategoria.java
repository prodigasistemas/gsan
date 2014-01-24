package gcom.cadastro.imovel;

import java.util.Date;

public interface IImovelSubcategoria {
	
	public ImovelSubcategoriaPK getComp_id();
	
	public void setComp_id(ImovelSubcategoriaPK compId);
	
	public short getQuantidadeEconomias();
	
	public void setQuantidadeEconomias(short getQuantidadeEconomias);
	
	public Date getUltimaAlteracao();
	
	public void setUltimaAlteracao(Date ultimaAlteracao);

}
