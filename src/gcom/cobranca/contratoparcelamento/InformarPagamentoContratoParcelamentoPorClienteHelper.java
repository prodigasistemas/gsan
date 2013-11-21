package gcom.cobranca.contratoparcelamento;

import java.io.Serializable;

/**
 * [UC1146] Informar Pagamento Contrato de Parcelamento por Cliente
 * 
 * @author Mariana Victor
 * @since 27/05/2011
 */
public class InformarPagamentoContratoParcelamentoPorClienteHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;

	private String idContrato;
	
	private String numeroContrato;
	
	private String valorParcelado;
	
	private String numeroParcelas;
	
	private String valorPago;
	
	private String numeroParcelasPagas;

	public InformarPagamentoContratoParcelamentoPorClienteHelper() {
		super();
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public String getNumeroParcelasPagas() {
		return numeroParcelasPagas;
	}

	public void setNumeroParcelasPagas(String numeroParcelasPagas) {
		this.numeroParcelasPagas = numeroParcelasPagas;
	}

	public String getValorPago() {
		return valorPago;
	}

	public void setValorPago(String valorPago) {
		this.valorPago = valorPago;
	}

	public String getValorParcelado() {
		return valorParcelado;
	}

	public void setValorParcelado(String valorParcelado) {
		this.valorParcelado = valorParcelado;
	}

}
