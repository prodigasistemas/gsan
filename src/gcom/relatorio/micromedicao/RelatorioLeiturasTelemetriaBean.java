package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioLeiturasTelemetriaBean implements RelatorioBean {
	
	
	private String dataLeitura;
	private String inscricao;
	private String leitura;
	private String numeroHidrometro;
	private String imovel;
	private String tipoMedicao;
	
	
	
	public RelatorioLeiturasTelemetriaBean() {
	}


	public RelatorioLeiturasTelemetriaBean(String dataLeitura,
			String inscricao, String leitura, String numeroHidrometro,
			String imovel, String tipoMedicao) {
		this.dataLeitura = dataLeitura;
		this.inscricao = inscricao;
		this.leitura = leitura;
		this.numeroHidrometro = numeroHidrometro;
		this.imovel = imovel;
		this.tipoMedicao = tipoMedicao;
	}
	
	
	public RelatorioLeiturasTelemetriaBean(String inscricao,String imovel) {
		this.imovel = imovel;
		this.inscricao = inscricao;
	}
	
	
	public String getDataLeitura() {
		return dataLeitura;
	}
	public void setDataLeitura(String dataLeitura) {
		this.dataLeitura = dataLeitura;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getLeitura() {
		return leitura;
	}
	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}
	public String getNumeroHidrometro() {
		return numeroHidrometro;
	}
	public void setNumeroHidrometro(String numeroHidrometro) {
		this.numeroHidrometro = numeroHidrometro;
	}
	public String getImovel() {
		return imovel;
	}
	public void setImovel(String imovel) {
		this.imovel = imovel;
	}
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}

	
}
