package gcom.gui.financeiro;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class GerarLancamentosContabeisArrecadacaoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAno;

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		mesAno = null;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
}
