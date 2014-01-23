package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;

public class ImovelSubcategoriaRetorno implements IImovelSubcategoria {
	
	private ImovelSubcategoriaPK comp_id;
	private short quantidadeEconomias;
	
	public ImovelSubcategoriaPK getComp_id() {
		return comp_id;
	}
	public void setComp_id(ImovelSubcategoriaPK comp_id) {
		this.comp_id = comp_id;
	}
	public short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	
	public ImovelSubcategoriaRetorno() {
		
	}
	
	public ImovelSubcategoriaRetorno(IImovelSubcategoria imovelSubcategoria) {
		this.comp_id = imovelSubcategoria.getComp_id();
		this.quantidadeEconomias = imovelSubcategoria.getQuantidadeEconomias();
	}
	

}
