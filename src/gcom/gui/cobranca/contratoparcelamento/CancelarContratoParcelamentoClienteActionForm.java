package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.action.ActionForm;

public class CancelarContratoParcelamentoClienteActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idClienteContrato;
	private String numeroContrato;
	
	private String idCliente;
	private String nomeCliente;
	private String cnpjCliente;
	private String idMotivoCancelamento;
	private String dataCancelamento;
	private String descricaoClienteRelacaoTipo;
	private String dataImplantacaoContrato;
	
	public String getDescricaoClienteRelacaoTipo() {
		return descricaoClienteRelacaoTipo;
	}
	public void setDescricaoClienteRelacaoTipo(String descricaoClienteRelacaoTipo) {
		this.descricaoClienteRelacaoTipo = descricaoClienteRelacaoTipo;
	}
	public String getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(String dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getIdMotivoCancelamento() {
		return idMotivoCancelamento;
	}
	public void setIdMotivoCancelamento(String idMotivoCancelamento) {
		this.idMotivoCancelamento = idMotivoCancelamento;
	}
	public String getDataImplantacaoContrato() {
		return dataImplantacaoContrato;
	}
	public void setDataImplantacaoContrato(String dataImplantacaoContrato) {
		this.dataImplantacaoContrato = dataImplantacaoContrato;
	}
	public String getIdClienteContrato() {
		return idClienteContrato;
	}
	public void setIdClienteContrato(String idClienteContrato) {
		this.idClienteContrato = idClienteContrato;
	}


}
