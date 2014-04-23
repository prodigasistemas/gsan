package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC1107] Manter Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 23/12/2010
 */
public class AtualizarCustoPavimentoPorRepavimentadoraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeRepavimentadora;
	private String descricaoUnidadeRepavimentadora; 
	
	// Dados Pavimento Rua
	private String idTipoPavimentoRua; 
	private String descricaoTipoPavimentoRua; 
	private String dataVigenciaInicialPavimentoRua;
	private String dataVigenciaFinalPavimentoRua;
	private String valorPavimentoRua;
	
	// Dados Pavimento Calçada
	private String idTipoPavimentoCalcada; 
	private String descricaoTipoPavimentoCalcada;
	private String dataVigenciaInicialPavimentoCalcada;
	private String dataVigenciaFinalPavimentoCalcada;
	private String valorPavimentoCalcada;
	
	private String situacaoVigenciaRua;
	private String situacaoVigenciaCalcada;
	
	public void reset(){

		this.idUnidadeRepavimentadora = null;
		this.descricaoUnidadeRepavimentadora = null;
		this.idTipoPavimentoRua = null;
		this.dataVigenciaInicialPavimentoRua = null;
		this.dataVigenciaFinalPavimentoRua = null;
		this.idTipoPavimentoCalcada = null;
		this.dataVigenciaInicialPavimentoCalcada = null;
		this.dataVigenciaFinalPavimentoCalcada = null;
		this.situacaoVigenciaRua = null;
		this.situacaoVigenciaCalcada = null;
		this.valorPavimentoRua = null;
		this.valorPavimentoCalcada = null;
		this.descricaoTipoPavimentoRua = null;
		this.descricaoTipoPavimentoCalcada = null;
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

	public String getDescricaoUnidadeRepavimentadora() {
		return descricaoUnidadeRepavimentadora;
	}

	public void setDescricaoUnidadeRepavimentadora(
			String descricaoUnidadeRepavimentadora) {
		this.descricaoUnidadeRepavimentadora = descricaoUnidadeRepavimentadora;
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

	public String getValorPavimentoCalcada() {
		return valorPavimentoCalcada;
	}

	public void setValorPavimentoCalcada(String valorPavimentoCalcada) {
		this.valorPavimentoCalcada = valorPavimentoCalcada;
	}

	public String getValorPavimentoRua() {
		return valorPavimentoRua;
	}

	public void setValorPavimentoRua(String valorPavimentoRua) {
		this.valorPavimentoRua = valorPavimentoRua;
	}

	public String getDescricaoTipoPavimentoCalcada() {
		return descricaoTipoPavimentoCalcada;
	}

	public void setDescricaoTipoPavimentoCalcada(
			String descricaoTipoPavimentoCalcada) {
		this.descricaoTipoPavimentoCalcada = descricaoTipoPavimentoCalcada;
	}

	public String getDescricaoTipoPavimentoRua() {
		return descricaoTipoPavimentoRua;
	}

	public void setDescricaoTipoPavimentoRua(String descricaoTipoPavimentoRua) {
		this.descricaoTipoPavimentoRua = descricaoTipoPavimentoRua;
	}

}
