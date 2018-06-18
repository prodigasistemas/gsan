package gcom.cadastro.imovel;

import java.io.Serializable;

public class ContratoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Contrato contrato;
	
	private String valorTarifa;
	
	private String consumoContratado;

	public ContratoHelper() {
		super();
	}

	public String getConsumoContratado() {
		return consumoContratado;
	}

	public void setConsumoContratado(String consumoContratado) {
		this.consumoContratado = consumoContratado;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}
	
}
