package gcom.gui.seguranca.acesso;

import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

public class ResultadoPesquisaControleLiberacaoPMEPActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<ControleLiberacaoPermissaoEspecial> controles;

	public Collection<ControleLiberacaoPermissaoEspecial> getControles() {
		return controles;
	}

	public void setControles(Collection<ControleLiberacaoPermissaoEspecial> controles) {
		this.controles = controles;
	}

	
}
