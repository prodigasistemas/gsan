package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtividadeCronograma;

import java.io.Serializable;

public class FaturamentoAtividadeAtualizarHelp implements Serializable{


	
	private static final long serialVersionUID = 1L;

	private FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
	
	private String quantidadeDias;
	
	private String dataPrevista;
	
	private Integer id;
	
	private String dataRealizacaoMesAnterior;
	
	private String idFaturamentoAtividade;

	public String getIdFaturamentoAtividade() {
		return idFaturamentoAtividade;
	}

	public void setIdFaturamentoAtividade(String idFaturamentoAtividade) {
		this.idFaturamentoAtividade = idFaturamentoAtividade;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the dataPrevista.
	 */
	public String getDataPrevista() {
		return dataPrevista;
	}

	/**
	 * @param dataPrevista The dataPrevista to set.
	 */
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	/**
	 * @return Returns the faturamentoAtividadeCronograma.
	 */
	public FaturamentoAtividadeCronograma getFaturamentoAtividadeCronograma() {
		return faturamentoAtividadeCronograma;
	}

	/**
	 * @param faturamentoAtividadeCronograma The faturamentoAtividadeCronograma to set.
	 */
	public void setFaturamentoAtividadeCronograma(
			FaturamentoAtividadeCronograma faturamentoAtividadeCronograma) {
		this.faturamentoAtividadeCronograma = faturamentoAtividadeCronograma;
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

	public String getDataRealizacaoMesAnterior() {
		return dataRealizacaoMesAnterior;
	}

	public void setDataRealizacaoMesAnterior(String dataRealizacaoMesAnterior) {
		this.dataRealizacaoMesAnterior = dataRealizacaoMesAnterior;
	}
	
	

}
