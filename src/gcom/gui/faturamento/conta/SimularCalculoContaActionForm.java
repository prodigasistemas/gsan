package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorActionForm;

public class SimularCalculoContaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	String mesAnoReferencia;
	String ligacaoAguaSituacaoID;
	String ligacaoEsgotoSituacaoID;
	String consumoFaturadoAgua;
	String consumoFaturadoEsgoto;
	String percentualEsgoto;
	String consumoTarifaID;
	String faturamentoGrupoID;
	String area;
	Integer totalEconomia;
	String situacaoMensagem;
	String percentualColeta;
	String consumoFaturadoPoco;
	String numeroMoradores;
	String pontosUtilizacao;
	
	public String getPercentualColeta() {
		return percentualColeta;
	}
	public void setPercentualColeta(String percentualColeta) {
		this.percentualColeta = percentualColeta;
	}
	public String getConsumoFaturadoAgua() {
		return consumoFaturadoAgua;
	}
	public void setConsumoFaturadoAgua(String consumoFaturadoAgua) {
		this.consumoFaturadoAgua = consumoFaturadoAgua;
	}
	public String getConsumoFaturadoEsgoto() {
		return consumoFaturadoEsgoto;
	}
	public void setConsumoFaturadoEsgoto(String consumoFaturadoEsgoto) {
		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}
	public String getConsumoTarifaID() {
		return consumoTarifaID;
	}
	public void setConsumoTarifaID(String consumoTarifaID) {
		this.consumoTarifaID = consumoTarifaID;
	}
	public String getFaturamentoGrupoID() {
		return faturamentoGrupoID;
	}
	public void setFaturamentoGrupoID(String faturamentoGrupoID) {
		this.faturamentoGrupoID = faturamentoGrupoID;
	}
	public String getLigacaoAguaSituacaoID() {
		return ligacaoAguaSituacaoID;
	}
	public void setLigacaoAguaSituacaoID(String ligacaoAguaSituacaoID) {
		this.ligacaoAguaSituacaoID = ligacaoAguaSituacaoID;
	}
	public String getLigacaoEsgotoSituacaoID() {
		return ligacaoEsgotoSituacaoID;
	}
	public void setLigacaoEsgotoSituacaoID(String ligacaoEsgotoSituacaoID) {
		this.ligacaoEsgotoSituacaoID = ligacaoEsgotoSituacaoID;
	}
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}
	/**
	 * @return Retorna o campo totalEconomia.
	 */
	public Integer getTotalEconomia() {
		return totalEconomia;
	}
	/**
	 * @param totalEconomia O totalEconomia a ser setado.
	 */
	public void setTotalEconomia(Integer totalEconomia) {
		this.totalEconomia = totalEconomia;
	}
	/**
	 * @return Retorna o campo situacaoMensagem.
	 */
	public String getSituacaoMensagem() {
		return situacaoMensagem;
	}
	/**
	 * @param situacaoMensagem O situacaoMensagem a ser setado.
	 */
	public void setSituacaoMensagem(String situacaoMensagem) {
		this.situacaoMensagem = situacaoMensagem;
	}
	/**
	 * @return Retorna o campo area.
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area O area a ser setado.
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getConsumoFaturadoPoco() {
		return consumoFaturadoPoco;
	}
	
	public void setConsumoFaturadoPoco(String consumoFaturadoPoco) {
		this.consumoFaturadoPoco = consumoFaturadoPoco;
	}
	public String getNumeroMoradores() {
		return numeroMoradores;
	}
	public void setNumeroMoradores(String numeroMoradores) {
		this.numeroMoradores = numeroMoradores;
	}
	public String getPontosUtilizacao() {
		return pontosUtilizacao;
	}
	public void setPontosUtilizacao(String pontosUtilizacao) {
		this.pontosUtilizacao = pontosUtilizacao;
	}

}
