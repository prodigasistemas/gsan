package gcom.arrecadacao.bean;

import gcom.arrecadacao.ContratoDemanda;

import java.io.Serializable;

public class ContratoDemandaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ContratoDemanda contratoDemanda;
	
	private String valorTarifa;
	
	private String consumoContratado;

	public ContratoDemandaHelper() {
		super();
	}

	public String getConsumoContratado() {
		return consumoContratado;
	}

	public void setConsumoContratado(String consumoContratado) {
		this.consumoContratado = consumoContratado;
	}

	public ContratoDemanda getContratoDemanda() {
		return contratoDemanda;
	}

	public void setContratoDemanda(ContratoDemanda contratoDemanda) {
		this.contratoDemanda = contratoDemanda;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}
	
}
