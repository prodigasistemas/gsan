package gcom.gui.cobranca.cobrancaporresultado;

import gcom.atendimentopublico.ordemservico.OrdemServico;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class MovimentarOrdemServicoEncerrarOSHelper implements
		Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<OrdemServico> colecaoOrdemServico;
	
	private Date dataEncerramento;
	
	private String observacaoEncerramento;
	
	private String idMotivoEncerramento;
	

	public MovimentarOrdemServicoEncerrarOSHelper() {
		super();
	}


	public Collection<OrdemServico> getColecaoOrdemServico() {
		return colecaoOrdemServico;
	}


	public void setColecaoOrdemServico(Collection<OrdemServico> colecaoOrdemServico) {
		this.colecaoOrdemServico = colecaoOrdemServico;
	}


	public Date getDataEncerramento() {
		return dataEncerramento;
	}


	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}


	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}


	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}


	public String getObservacaoEncerramento() {
		return observacaoEncerramento;
	}


	public void setObservacaoEncerramento(String observacaoEncerramento) {
		this.observacaoEncerramento = observacaoEncerramento;
	}
	
}
