package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoProblemasLeiturasAnormalidadeBean implements RelatorioBean{
	
	private String erro;

	
	public RelatorioResumoProblemasLeiturasAnormalidadeBean(String erro) {
		super();
		this.erro = erro;
	}

	/**
	 * @return Returns the erro.
	 */
	public String getErro() {
		return erro;
	}

	/**
	 * @param erro The erro to set.
	 */
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	

}
