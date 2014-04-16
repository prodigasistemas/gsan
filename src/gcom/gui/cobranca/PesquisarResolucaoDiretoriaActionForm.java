package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0223] Pesquisar Resolução de Diretoria
 * @author Viviann Sousa
 * @since 19/04/2006
 */
public class PesquisarResolucaoDiretoriaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroResolucaoDiretoria;

	private String dataInicioVigencia;
	
	private String dataFimVigencia;


	public String getDataFimVigencia() {
		return dataFimVigencia;
	}


	public void setDataFimVigencia(String dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}


	public String getDataInicioVigencia() {
		return dataInicioVigencia;
	}


	public void setDataInicioVigencia(String dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}


	public String getNumeroResolucaoDiretoria() {
		return numeroResolucaoDiretoria;
	}


	public void setNumeroResolucaoDiretoria(String numeroResolucaoDiretoria) {
		this.numeroResolucaoDiretoria = numeroResolucaoDiretoria;
	}

	



}
