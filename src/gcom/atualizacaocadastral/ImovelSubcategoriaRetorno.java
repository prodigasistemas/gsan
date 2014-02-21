package gcom.atualizacaocadastral;

import gcom.cadastro.imovel.IImovelSubcategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;

import java.util.Date;

public class ImovelSubcategoriaRetorno implements IImovelSubcategoria {
	
	private Integer id;

	private gcom.cadastro.imovel.Imovel imovel;

    private gcom.cadastro.imovel.Subcategoria subcategoria;
	
	private short quantidadeEconomias;
	
	private Date ultimaAlteracao;
	
	private Integer idImovelRetorno;
	
	public ImovelSubcategoriaRetorno() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Integer getIdImovelRetorno() {
		return idImovelRetorno;
	}
	public void setIdImovelRetorno(Integer idImovelRetorno) {
		this.idImovelRetorno = idImovelRetorno;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
		
		if (subcategoria != null && subcategoria.getQuantidadeEconomias() != null){
			this.setQuantidadeEconomias(subcategoria.getQuantidadeEconomias().shortValue());
		}
	}
}