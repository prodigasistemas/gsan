package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;


/**
 * [UC0991] Filtrar Faturamento Imediato Ajuste
 * 
 * @author Hugo Leonardo
 *
 * @date 26/02/2010
 */

public class FiltrarFaturamentoImediatoAjusteActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private String mesAnoReferencia;

    /** nullable persistent field */
    private String faturamentoGrupo;

    /** nullable persistent field */
    private String imovelId;
    
    private String imovelInscricao;

    /** nullable persistent field */
    private String rotaId;
    
    private String codigoRota;
    
    /** default constructor */
    public FiltrarFaturamentoImediatoAjusteActionForm() {
    }

	/**
	 * @return Returns the faturamentoGrupo.
	 */
	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	/**
	 * @param faturamentoGrupo The faturamentoGrupo to set.
	 */
	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}


	/**
	 * @return Returns the mesAnoReferencia.
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia The mesAnoReferencia to set.
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return Returns the imovelId.
	 */
	public String getImovelId() {
		return imovelId;
	}

	/**
	 * @param imovelId The imovelId to set.
	 */
	public void setImovelId(String imovelId) {
		this.imovelId = imovelId;
	}

	/**
	 * @return Returns the imovelInscricao.
	 */
	public String getImovelInscricao() {
		return imovelInscricao;
	}

	/**
	 * @param imovelInscricao The imovelInscricao to set.
	 */
	public void setImovelInscricao(String imovelInscricao) {
		this.imovelInscricao = imovelInscricao;
	}

	/**
	 * @return Returns the rotaId.
	 */
	public String getRotaId() {
		return rotaId;
	}

	/**
	 * @param rotaId The rotaId to set.
	 */
	public void setRotaId(String rotaId) {
		this.rotaId = rotaId;
	}

	/**
	 * @return Returns the codigoRota.
	 */
	public String getCodigoRota() {
		return codigoRota;
	}

	/**
	 * @param codigoRota The codigoRota to set.
	 */
	public void setCodigoRota(String codigoRota) {
		this.codigoRota = codigoRota;
	}

}
