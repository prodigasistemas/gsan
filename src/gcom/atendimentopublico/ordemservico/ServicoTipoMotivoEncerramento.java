package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;

import java.io.Serializable;
import java.util.Date;

public class ServicoTipoMotivoEncerramento implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private ServicoTipo servicoTipo;
	
	private AtendimentoMotivoEncerramento motivoEncerramento;
	
	private Date ultimaAlteracao;

	public Integer getId() {
		return id;
	}

	public AtendimentoMotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMotivoEncerramento(
			AtendimentoMotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
