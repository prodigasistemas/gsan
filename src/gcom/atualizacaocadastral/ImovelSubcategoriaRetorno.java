package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaPK;
import java.util.Date;

public class ImovelSubcategoriaRetorno implements IImovelSubcategoria {
	
	private ImovelSubcategoriaPK comp_id;
	private short quantidadeEconomias;
	private Date ultimaAlteracao;
	
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
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
