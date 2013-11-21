package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class InformarRotaLeituristaActionForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;

	private String empresaID;
	
	private String leitursitaID;
	
	private String rota;
	
	private String descricaoRota;
	
	private String[] rotas;

	/**
	 * @return Returns the descricaoRota.
	 */
	public String getDescricaoRota() {
		return descricaoRota;
	}

	/**
	 * @param descricaoRota The descricaoRota to set.
	 */
	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}

	/**
	 * @return Returns the empresaID.
	 */
	public String getEmpresaID() {
		return empresaID;
	}

	/**
	 * @param empresaID The empresaID to set.
	 */
	public void setEmpresaID(String empresaID) {
		this.empresaID = empresaID;
	}

	/**
	 * @return Returns the leitursitaID.
	 */
	public String getLeitursitaID() {
		return leitursitaID;
	}

	/**
	 * @param leitursitaID The leitursitaID to set.
	 */
	public void setLeitursitaID(String leitursitaID) {
		this.leitursitaID = leitursitaID;
	}

	/**
	 * @return Returns the rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota The rota to set.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Returns the rotas.
	 */
	public String[] getRotas() {
		return rotas;
	}

	/**
	 * @param rotas The rotas to set.
	 */
	public void setRotas(String[] rotas) {
		this.rotas = rotas;
	}
	
	
	
	
	
	

}
