package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0244] Manter Comando de Ação de Cobrança
 * @author Rafael Santos
 * @since 22/03/2006
 */
public class ManterComandoAcaoCobrancaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idCobrancaAcaoAtividadeCronograma;
	
	private String[] idCobrancaAcaoAtividadeComando;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo idCobrancaAcaoAtividadeComando.
	 */
	public String[] getIdCobrancaAcaoAtividadeComando() {
		return idCobrancaAcaoAtividadeComando;
	}

	/**
	 * @param idCobrancaAcaoAtividadeComando O idCobrancaAcaoAtividadeComando a ser setado.
	 */
	public void setIdCobrancaAcaoAtividadeComando(
			String[] idCobrancaAcaoAtividadeComando) {
		this.idCobrancaAcaoAtividadeComando = idCobrancaAcaoAtividadeComando;
	}

	/**
	 * @return Retorna o campo idCobrancaAcaoAtividadeCronograma.
	 */
	public String[] getIdCobrancaAcaoAtividadeCronograma() {
		return idCobrancaAcaoAtividadeCronograma;
	}

	/**
	 * @param idCobrancaAcaoAtividadeCronograma O idCobrancaAcaoAtividadeCronograma a ser setado.
	 */
	public void setIdCobrancaAcaoAtividadeCronograma(
			String[] idCobrancaAcaoAtividadeCronograma) {
		this.idCobrancaAcaoAtividadeCronograma = idCobrancaAcaoAtividadeCronograma;
	}

}
