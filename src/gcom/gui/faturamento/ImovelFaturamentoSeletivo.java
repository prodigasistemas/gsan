package gcom.gui.faturamento;

import gcom.gui.micromedicao.DadosMovimentacao;

public class ImovelFaturamentoSeletivo {

	private Integer idImovel;
	private Integer leitura;
	private Integer anormalidade;
	private String dataLeitura;
	private DadosMovimentacao dadoMovimentacao;
	
	public ImovelFaturamentoSeletivo() {
	}
	
	public ImovelFaturamentoSeletivo(Integer id, Integer leitura, Integer anormalidade, String dataLeitura) {
		this.idImovel = id;
		this.leitura = leitura;
		this.anormalidade = anormalidade;
		this.dataLeitura = dataLeitura;
	}

	public Integer getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	public Integer getLeitura() {
		return leitura;
	}
	
	public void setLeitura(Integer leitura) {
		this.leitura = leitura;
	}
	
	public Integer getAnormalidade() {
		return anormalidade;
	}
	
	public void setAnormalidade(Integer anormalidade) {
		this.anormalidade = anormalidade;
	}
	
	public String getDataLeitura() {
		return dataLeitura;
	}
	
	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	public DadosMovimentacao getDadoMovimentacao() {
		return dadoMovimentacao;
	}

	public void setDadoMovimentacao(DadosMovimentacao dadoMovimentacao) {
		this.dadoMovimentacao = dadoMovimentacao;
	}
}
