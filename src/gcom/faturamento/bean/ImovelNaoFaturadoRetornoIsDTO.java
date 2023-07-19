package gcom.faturamento.bean;

import gcom.cadastro.imovel.Imovel;

public class ImovelNaoFaturadoRetornoIsDTO {
	
	private Imovel imovel;
	private String descricao;
	
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
