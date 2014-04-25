package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author José Guilherme Macedo Vieira
 * @created 22/06/2009
 */
public class RelatorioImpostosPorClienteResponsavelActionForm extends ValidatorActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String indicadorTipoImposto;
		
	//O ano e o mês de referencia da pesquisa realizada
	//no formato MM/YYYY
	private String anoMesReferencia;
	
	//O ID do cliente selecionado na pesquisa
	private String clienteID;
	
	//Só pode ser 2 valores - sintetico ou analitico	
	private String relatorioTipo;
	
	private String nomeCliente;


	public RelatorioImpostosPorClienteResponsavelActionForm() {

	}

	public String getRelatorioTipo() {
		return relatorioTipo;
	}
	
	public void setRelatorioTipo(String relatorioTipo) {
		this.relatorioTipo = relatorioTipo;
	}
	
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	
	public String getClienteID() {
		return clienteID;
	}

	public void setClienteID(String clienteID) {
		this.clienteID = clienteID;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getIndicadorTipoImposto() {
		return indicadorTipoImposto;
	}

	public void setIndicadorTipoImposto(String indicadorTipoImposto) {
		this.indicadorTipoImposto = indicadorTipoImposto;
	}	
}
