package gcom.gui.cobranca.contratoparcelamento;

import org.apache.struts.action.ActionForm;

/**
 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 * @since 27/05/2011
 */
public class InformarPagamentoContratoParcelamentoPorClienteActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String numeroContrato;
	
	private String idCliente;

	private String nomeCliente;
	
	private String idRegistro;
	
	private String totalSelecionado;
	
	private String idArrecadador;
	
	private String nomeArrecadador;
	
	private String numeroParcela;
	
	private String valorParcelado;
	
	private String dataPagamento;
	
	public InformarPagamentoContratoParcelamentoPorClienteActionForm() {
		super();
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

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getTotalSelecionado() {
		return totalSelecionado;
	}

	public void setTotalSelecionado(String totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}

	public String getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(String idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public String getNomeArrecadador() {
		return nomeArrecadador;
	}

	public void setNomeArrecadador(String nomeArrecadador) {
		this.nomeArrecadador = nomeArrecadador;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public String getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

}
