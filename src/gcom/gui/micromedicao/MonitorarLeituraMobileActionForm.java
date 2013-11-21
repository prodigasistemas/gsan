package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class MonitorarLeituraMobileActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	
	private String descricaoRota;
	
	private String rota;
	
	private String leiturista;
	
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
	 * @return Returns the leiturista.
	 */
	public String getLeiturista() {
		return leiturista;
	}

	/**
	 * @param leiturista The leiturista to set.
	 */
	public void setLeiturista(String leiturista) {
		this.leiturista = leiturista;
	}

	/**
	 * @return Returns the mesAno.
	 */
	public String getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno The mesAno to set.
	 */
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
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

	

	
	
	

}
