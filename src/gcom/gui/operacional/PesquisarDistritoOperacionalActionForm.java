package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0780] Pesquisar Distrito Operacional
 * 
 * @date 05/05/2008
 * @author Arthur Carvalho
 */

public class PesquisarDistritoOperacionalActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeDistritoOperacional;

	private String setorAbastecimento;
	
	private String zonaAbastecimento;
	
	private String indicadorUso;

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getNomeDistritoOperacional() {
		return nomeDistritoOperacional;
	}

	public void setNomeDistritoOperacional(String nomeDistritoOperacional) {
		this.nomeDistritoOperacional = nomeDistritoOperacional;
	}

	public String getZonaAbastecimento() {
		return zonaAbastecimento;
	}

	public void setZonaAbastecimento(String zonaAbastecimento) {
		this.zonaAbastecimento = zonaAbastecimento;
	}

	public String getSetorAbastecimento() {
		return setorAbastecimento;
	}

	public void setSetorAbastecimento(String setorAbastecimento) {
		this.setorAbastecimento = setorAbastecimento;
	}

}
