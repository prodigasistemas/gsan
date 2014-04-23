package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0512]	Inserir Contrato de Demanda 
 * 
 * @author Eduardo Bianchi 
 * @date 14/02/2007
 */

public class AtualizarContratoDemandaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String numeroContrato;
    private String inscricaoImovel;
    private String idImovel;
    private String dataInicioContrato;
    private String dataFimContrato;
    private String dataEncerramento;
    private String idMotivoCancelamento;
    
	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	/**
	 * @param dataEncerramento O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	/**
	 * @return Retorna o campo idMotivoEncerramento.
	 */
	public String getIdMotivoCancelamento() {
		return idMotivoCancelamento;
	}
	/**
	 * @param idMotivoEncerramento O idMotivoEncerramento a ser setado.
	 */
	public void setIdMotivoCancelamento(String idMotivoCancelamento) {
		this.idMotivoCancelamento = idMotivoCancelamento;
	}
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

