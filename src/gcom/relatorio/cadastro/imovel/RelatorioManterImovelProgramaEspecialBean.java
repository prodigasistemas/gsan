package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;

public class RelatorioManterImovelProgramaEspecialBean implements RelatorioBean {
	private String id;
	private String observacao;
	private String dataApresentacao;
	private String mesAnoEntrada;
	private String mesAnoSaida;
	private String dataInicio;
	private String dataFim;
	
	public RelatorioManterImovelProgramaEspecialBean(String id,
			String observacao, String dataApresentacao,
			String mesAnoEntrada, String mesAnoSaida,
			String dataInicio,
			String dataFim) {
		this.id = id;
		this.observacao = observacao;
		this.dataApresentacao = dataApresentacao;
		this.mesAnoEntrada = mesAnoEntrada;
		this.mesAnoSaida = mesAnoSaida;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDataApresentacao() {
		return dataApresentacao;
	}
	public void setDataApresentacao(String dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getMesAnoEntrada() {
		return mesAnoEntrada;
	}
	public void setMesAnoEntrada(String mesAnoEntrada) {
		this.mesAnoEntrada = mesAnoEntrada;
	}
	public String getMesAnoSaida() {
		return mesAnoSaida;
	}
	public void setMesAnoSaida(String mesAnoSaida) {
		this.mesAnoSaida = mesAnoSaida;
	}
	
	
}
