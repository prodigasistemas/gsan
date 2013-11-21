package gcom.relatorio.cadastro.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioNotificacaoDebitosImpressaoSimultaneaBean implements RelatorioBean{
	private static final long serialVersionUID = 1L;
	
	private String anoMesReferencia;
	private String empresa;	
	private String grupo;
	private String localidadeID;
	private String localidade;	
	private String setorComercial;
	private String rota;
	private int quantidade;
			
	
	public String getLocalidadeID() {
		return localidadeID;
	}
	public void setLocalidadeID(String localidadeID) {
		this.localidadeID = localidadeID;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}	
	public String getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getRota() {
		return rota;
	}
	public void setRota(String rota) {
		this.rota = rota;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
		
		
}