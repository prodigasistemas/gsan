package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class ProcessarLeiturasNaoRegistradasActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String faturamentoGrupoID;
	
	private String qntNaoProcessado;

	/**
	 * @return Returns the faturamentoGrupoID.
	 */
	public String getFaturamentoGrupoID() {
		return faturamentoGrupoID;
	}

	/**
	 * @param faturamentoGrupoID The faturamentoGrupoID to set.
	 */
	public void setFaturamentoGrupoID(String faturamentoGrupoID) {
		this.faturamentoGrupoID = faturamentoGrupoID;
	}

	/**
	 * @return Returns the qntNaoProcessado.
	 */
	public String getQntNaoProcessado() {
		return qntNaoProcessado;
	}

	/**
	 * @param qntNaoProcessado The qntNaoProcessado to set.
	 */
	public void setQntNaoProcessado(String qntNaoProcessado) {
		this.qntNaoProcessado = qntNaoProcessado;
	}
	
	

}
