package gcom.gui.arrecadacao.pagamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe 
 *
 * @author Sávio Luiz
 * @date 19/12/2007
 */
public class InformarExpurgoPagamentoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idCliente;
	private String nomeCliente;
	private String dataPagamento;
	private String quantidadePagamentosExpurgados;
	private String quantidadePagamentosNaoExpurgados;
	private String mesAnoReferencia;
	

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		
	}


	public String getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
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


	public String getQuantidadePagamentosExpurgados() {
		return quantidadePagamentosExpurgados;
	}


	public void setQuantidadePagamentosExpurgados(
			String quantidadePagamentosExpurgados) {
		this.quantidadePagamentosExpurgados = quantidadePagamentosExpurgados;
	}


	public String getQuantidadePagamentosNaoExpurgados() {
		return quantidadePagamentosNaoExpurgados;
	}


	public void setQuantidadePagamentosNaoExpurgados(
			String quantidadePagamentosNaoExpurgados) {
		this.quantidadePagamentosNaoExpurgados = quantidadePagamentosNaoExpurgados;
	}


	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}


	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

}
