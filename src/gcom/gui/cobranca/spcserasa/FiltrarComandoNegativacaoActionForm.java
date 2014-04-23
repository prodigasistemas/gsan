package gcom.gui.cobranca.spcserasa;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Classe que representa o fomr da pagina de exibição do filtro 
 * para pesquisa de comandos de negativação
 * 
 * @author: Thiago Vieira
 * @date: 10/1/2007
 */

public class FiltrarComandoNegativacaoActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String tipoComando = "";
	
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	this.tipoComando = "";

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
