package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0325] Consultar Comandos de Ação de Cobrança
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class ConsultarComandosAcaoCobrancaCronogramaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoComando;
	
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Retorna o campo tipoComando.
	 */
	public String getTipoComando() {
		return tipoComando;
	}

	/**
	 * @param tipoComando O tipoComando a ser setado.
	 */
	public void setTipoComando(String tipoComando) {
		this.tipoComando = tipoComando;
	}
}
