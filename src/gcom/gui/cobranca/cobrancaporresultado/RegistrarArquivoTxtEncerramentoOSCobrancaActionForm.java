package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;

public class RegistrarArquivoTxtEncerramentoOSCobrancaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String nomeEmpresa;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	

}
