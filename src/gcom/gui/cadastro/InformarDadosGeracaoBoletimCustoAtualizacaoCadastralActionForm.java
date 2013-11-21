package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

public class InformarDadosGeracaoBoletimCustoAtualizacaoCadastralActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String empresa = "";
	
	private String dataInicial = "";

	private String dataFinal = "";

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
