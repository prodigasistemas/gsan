package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0512]	Inserir Contrato de Demanda 
 * 
 * @author Eduardo Bianchi 
 * @date 14/02/2007
 */

public class InserirContratoDemandaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String numeroContrato;
    private String inscricaoImovel;
    private String idImovel;
    private String dataInicioContrato;
    private String dataFimContrato;
    
	public String getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(String dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public String getDataInicioContrato() {
		return dataInicioContrato;
	}
	public void setDataInicioContrato(String dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}  

}

