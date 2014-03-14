package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;


public class RelatorioInconsistenciasRetornoBean implements RelatorioBean {
	
	private String matricula;
	
	private String erros;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getErros() {
		return erros;
	}

	public void setErros(String erros) {
		this.erros = erros;
	}
}
