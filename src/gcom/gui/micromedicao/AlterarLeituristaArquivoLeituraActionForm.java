package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

public class AlterarLeituristaArquivoLeituraActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String empresaID;
	
	private String grupoFaturamentoID;
	
	private String mesAno;
	
	private String arquivoID;
	
	private String arquivoDivididoID;
	
	private String leitursitaID;
	
	private String tipoArquivo = "T";

	/**
	 * @return Returns the arquivoId.
	 */
	public String getArquivoID() {
		return arquivoID;
	}

	/**
	 * @param arquivoId The arquivoId to set.
	 */
	public void setArquivoID(String arquivoId) {
		this.arquivoID = arquivoId;
	}

	/**
	 * @return Returns the leituristaId.
	 */
	public String getLeitursitaID() {
		return leitursitaID;
	}

	/**
	 * @param leituristaId The leituristaId to set.
	 */
	public void setLeitursitaID(String leituristaId) {
		this.leitursitaID = leituristaId;
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
	 * @return Returns the grupoFaturamentoID.
	 */
	public String getGrupoFaturamentoID() {
		return grupoFaturamentoID;
	}

	/**
	 * @param grupoFaturamentoID The grupoFaturamentoID to set.
	 */
	public void setGrupoFaturamentoID(String grupoFaturamentoID) {
		this.grupoFaturamentoID = grupoFaturamentoID;
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

	public String getArquivoDivididoID() {
		return arquivoDivididoID;
	}

	public void setArquivoDivididoID(String arquivoDivididoID) {
		this.arquivoDivididoID = arquivoDivididoID;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	
	
	

}
