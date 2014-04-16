package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.action.ActionForm;

/**
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 *
 * @date 21/12/2010
 */

public class FiltrarCustoPavimentoPorRepavimentadoraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeRepavimentadora; 
	
	// Dados Pavimento Rua
	private String idTipoPavimentoRua; 
	private String dataVigenciaInicialPavimentoRua;
	private String dataVigenciaFinalPavimentoRua;
	
	// Dados Pavimento Calçada
	private String idTipoPavimentoCalcada; 
	private String dataVigenciaInicialPavimentoCalcada;
	private String dataVigenciaFinalPavimentoCalcada;
	
	private String atualizar;
	private String situacaoVigenciaRua;
	private String situacaoVigenciaCalcada;

	public void reset(){

		this.idUnidadeRepavimentadora = null;
		this.idTipoPavimentoRua = null;
		this.dataVigenciaInicialPavimentoRua = null;
		this.dataVigenciaFinalPavimentoRua = null;
		this.idTipoPavimentoCalcada = null;
		this.dataVigenciaInicialPavimentoCalcada = null;
		this.dataVigenciaFinalPavimentoCalcada = null;
		this.atualizar = null;
		this.situacaoVigenciaRua = null;
		this.situacaoVigenciaCalcada = null;
	}

	public String getDataVigenciaFinalPavimentoCalcada() {
		return dataVigenciaFinalPavimentoCalcada;
	}

	public void setDataVigenciaFinalPavimentoCalcada(
			String dataVigenciaFinalPavimentoCalcada) {
		this.dataVigenciaFinalPavimentoCalcada = dataVigenciaFinalPavimentoCalcada;
	}

	public String getDataVigenciaFinalPavimentoRua() {
		return dataVigenciaFinalPavimentoRua;
	}

	public void setDataVigenciaFinalPavimentoRua(
			String dataVigenciaFinalPavimentoRua) {
		this.dataVigenciaFinalPavimentoRua = dataVigenciaFinalPavimentoRua;
	}

	public String getDataVigenciaInicialPavimentoCalcada() {
		return dataVigenciaInicialPavimentoCalcada;
	}

	public void setDataVigenciaInicialPavimentoCalcada(
			String dataVigenciaInicialPavimentoCalcada) {
		this.dataVigenciaInicialPavimentoCalcada = dataVigenciaInicialPavimentoCalcada;
	}

	public String getDataVigenciaInicialPavimentoRua() {
		return dataVigenciaInicialPavimentoRua;
	}

	public void setDataVigenciaInicialPavimentoRua(
			String dataVigenciaInicialPavimentoRua) {
		this.dataVigenciaInicialPavimentoRua = dataVigenciaInicialPavimentoRua;
	}

	public String getIdTipoPavimentoCalcada() {
		return idTipoPavimentoCalcada;
	}

	public void setIdTipoPavimentoCalcada(String idTipoPavimentoCalcada) {
		this.idTipoPavimentoCalcada = idTipoPavimentoCalcada;
	}

	public String getIdTipoPavimentoRua() {
		return idTipoPavimentoRua;
	}

	public void setIdTipoPavimentoRua(String idTipoPavimentoRua) {
		this.idTipoPavimentoRua = idTipoPavimentoRua;
	}

	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	public String getSituacaoVigenciaCalcada() {
		return situacaoVigenciaCalcada;
	}

	public void setSituacaoVigenciaCalcada(String situacaoVigenciaCalcada) {
		this.situacaoVigenciaCalcada = situacaoVigenciaCalcada;
	}

	public String getSituacaoVigenciaRua() {
		return situacaoVigenciaRua;
	}

	public void setSituacaoVigenciaRua(String situacaoVigenciaRua) {
		this.situacaoVigenciaRua = situacaoVigenciaRua;
	}

}
