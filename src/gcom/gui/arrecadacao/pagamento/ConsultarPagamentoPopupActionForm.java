package gcom.gui.arrecadacao.pagamento;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Vivianne Sousa
 * @date 10/07/2007
 */
public class ConsultarPagamentoPopupActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String codigoAgenteArrecadador;
	private String nomeClienteArrecadador;	
	private String ultimaAlteracaoPagamento;
	
	public String getCodigoAgenteArrecadador() {
		return codigoAgenteArrecadador;
	}
	public void setCodigoAgenteArrecadador(String codigoAgenteArrecadador) {
		this.codigoAgenteArrecadador = codigoAgenteArrecadador;
	}
	public String getNomeClienteArrecadador() {
		return nomeClienteArrecadador;
	}
	public void setNomeClienteArrecadador(String nomeClienteArrecadador) {
		this.nomeClienteArrecadador = nomeClienteArrecadador;
	}
	public String getUltimaAlteracaoPagamento() {
		return ultimaAlteracaoPagamento;
	}
	public void setUltimaAlteracaoPagamento(String ultimaAlteracaoPagamento) {
		this.ultimaAlteracaoPagamento = ultimaAlteracaoPagamento;
	} 

}
