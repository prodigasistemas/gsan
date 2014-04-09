package gcom.gui.operacional.abastecimento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0526]	FILTRAR SISTEMA DE ESGOTO
 * 
 * @author Kássia Albuquerque
 * @date 12/03/2007
 */

public class FiltrarSistemaEsgotoActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricaoSistemaEsgoto;
	private String tipoPesquisa;
	private String divisaoEsgoto;
	private String tipoTratamento;
	private String indicadorUso;	
	
	public String getDescricaoSistemaEsgoto() {
		return descricaoSistemaEsgoto;
	}
	public void setDescricaoSistemaEsgoto(String descricaoSistemaEsgoto) {
		this.descricaoSistemaEsgoto = descricaoSistemaEsgoto;
	}
	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}
	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}
	public String getTipoTratamento() {
		return tipoTratamento;
	}
	public void setTipoTratamento(String tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}	
}
