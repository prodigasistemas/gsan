package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtividade;

import java.io.Serializable;

public class FaturamentoCronogramaAtividadeHelp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FaturamentoAtividade faturamentoAtividade;
	
	private String quantidadeDias;
	
	private String dataPrevista;
	
	private String dataRealizacaoMesAnterior;
	
	private String comandar;

	public String getComandar() {
		return comandar;
	}

	public void setComandar(String comandar) {
		this.comandar = comandar;
	}

	/**
	 * @return Returns the faturamentoAtividade.
	 */
	public FaturamentoAtividade getFaturamentoAtividade() {
		return faturamentoAtividade;
	}

	/**
	 * @param faturamentoAtividade The faturamentoAtividade to set.
	 */
	public void setFaturamentoAtividade(FaturamentoAtividade faturamentoAtividade) {
		this.faturamentoAtividade = faturamentoAtividade;
	}

	/**
	 * @return Returns the quantidadeDias.
	 */
	public String getQuantidadeDias() {
		return quantidadeDias;
	}

	/**
	 * @param quantidadeDias The quantidadeDias to set.
	 */
	public void setQuantidadeDias(String quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDataRealizacaoMesAnterior() {
		return dataRealizacaoMesAnterior;
	}

	public void setDataRealizacaoMesAnterior(String dataRealizacaoMesAnterior) {
		this.dataRealizacaoMesAnterior = dataRealizacaoMesAnterior;
	}
}
