package gcom.gui.batch;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form que manipula os dados do caso de uso Iniciar Processo
 * 
 * @author Rodrigo Silveira
 * @created 22/07/2006
 */
public class InserirProcessoMensalEventualActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idProcesso;

	private String descricaoProcesso;

	//private String idSituacaoProcesso;

	private String dataAgendamento;

	private String horaAgendamento;

	private String idProcessoIniciadoPrecedente;

	private String descricaoProcessoIniciadoPrecedente;

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getDescricaoProcesso() {
		return descricaoProcesso;
	}

	public void setDescricaoProcesso(String descricaoProcesso) {
		this.descricaoProcesso = descricaoProcesso;
	}

	public String getDescricaoProcessoIniciadoPrecedente() {
		return descricaoProcessoIniciadoPrecedente;
	}

	public void setDescricaoProcessoIniciadoPrecedente(
			String descricaoProcessoIniciadoPrecedente) {
		this.descricaoProcessoIniciadoPrecedente = descricaoProcessoIniciadoPrecedente;
	}

	public String getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public String getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(String idProcesso) {
		this.idProcesso = idProcesso;
	}

	public String getIdProcessoIniciadoPrecedente() {
		return idProcessoIniciadoPrecedente;
	}

	public void setIdProcessoIniciadoPrecedente(
			String idProcessoIniciadoPrecedente) {
		this.idProcessoIniciadoPrecedente = idProcessoIniciadoPrecedente;
	}


	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		this.idProcesso = null;
		this.descricaoProcesso = null;

		this.dataAgendamento = null;
		this.horaAgendamento = null;
		this.idProcessoIniciadoPrecedente = null;
		this.descricaoProcessoIniciadoPrecedente = null;
	}

}
