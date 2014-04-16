package gcom.gui.cobranca;

import org.apache.struts.validator.ValidatorForm;

public class CancelarDocumentosCobrancaActionForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCobrancaAcaoAtividadeCronograma;
	private Integer idCobrancaAcaoAtividadeComando;

	public Integer getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	public void setIdCobrancaAcaoAtividadeComando(
			Integer idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	public Integer getIdCobrancaAcaoAtividadeCronograma() {
		return idCobrancaAcaoAtividadeCronograma;
	}

	public void setIdCobrancaAcaoAtividadeCronograma(
			Integer idCobrancaAcaoAtividadeCronograma) {
		this.idCobrancaAcaoAtividadeCronograma = idCobrancaAcaoAtividadeCronograma;
	}

}
