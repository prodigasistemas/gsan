package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

public class RelatorioReleituraImoveisBean implements RelatorioBean{

	

	public RelatorioReleituraImoveisBean() {
		super();
	}
	private String status;
	
	private String localidade;
	
	private String setor;
	
	private String rota;
	
	private String quadra;
	
	private String imovel;
	
	private String leituraAnteriorAgua;
	
	private String leituraAtualAgua;
	
	private String leituraAnormalidadeAnteriorAgua;
	
	private String leituraAnormalidadeAtualAgua;
	
	private String leituraAnteriorPoco;
	
	private String leituraAtualPoco;
	
	private String leituraAnormalidadeAnteriorPoco;
	
	private String leituraAnormalidadeAtualPoco;
	
	private String recebeuMensagem;

	public String getImovel() {
		return imovel;
	}

	public void setImovel(String imovel) {
		this.imovel = imovel;
	}

	public String getLeituraAnormalidadeAnteriorAgua() {
		return leituraAnormalidadeAnteriorAgua;
	}

	public void setLeituraAnormalidadeAnteriorAgua(
			String leituraAnormalidadeAnteriorAgua) {
		this.leituraAnormalidadeAnteriorAgua = leituraAnormalidadeAnteriorAgua;
	}

	public String getLeituraAnormalidadeAnteriorPoco() {
		return leituraAnormalidadeAnteriorPoco;
	}

	public void setLeituraAnormalidadeAnteriorPoco(
			String leituraAnormalidadeAnteriorPoco) {
		this.leituraAnormalidadeAnteriorPoco = leituraAnormalidadeAnteriorPoco;
	}

	public String getLeituraAnormalidadeAtualAgua() {
		return leituraAnormalidadeAtualAgua;
	}

	public void setLeituraAnormalidadeAtualAgua(String leituraAnormalidadeAtualAgua) {
		this.leituraAnormalidadeAtualAgua = leituraAnormalidadeAtualAgua;
	}

	public String getLeituraAnormalidadeAtualPoco() {
		return leituraAnormalidadeAtualPoco;
	}

	public void setLeituraAnormalidadeAtualPoco(String leituraAnormalidadeAtualPoco) {
		this.leituraAnormalidadeAtualPoco = leituraAnormalidadeAtualPoco;
	}

	public String getLeituraAnteriorAgua() {
		return leituraAnteriorAgua;
	}

	public void setLeituraAnteriorAgua(String leituraAnteriorAgua) {
		this.leituraAnteriorAgua = leituraAnteriorAgua;
	}

	public String getLeituraAnteriorPoco() {
		return leituraAnteriorPoco;
	}

	public void setLeituraAnteriorPoco(String leituraAnteriorPoco) {
		this.leituraAnteriorPoco = leituraAnteriorPoco;
	}

	public String getLeituraAtualAgua() {
		return leituraAtualAgua;
	}

	public void setLeituraAtualAgua(String leituraAtualAgua) {
		this.leituraAtualAgua = leituraAtualAgua;
	}

	public String getLeituraAtualPoco() {
		return leituraAtualPoco;
	}

	public void setLeituraAtualPoco(String leituraAtualPoco) {
		this.leituraAtualPoco = leituraAtualPoco;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getRecebeuMensagem() {
		return recebeuMensagem;
	}

	public void setRecebeuMensagem(String recebeuMensagem) {
		this.recebeuMensagem = recebeuMensagem;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//public RelatorioReleituraImoveisBean(){}

	
}
