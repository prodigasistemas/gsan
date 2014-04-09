package gcom.gui.financeiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Flávio Leonardo
 * @since 16/04/2010
 */
public class GerarRelatorioResumoReceitaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String mesAnoReferencial;
	
	private String tipoRelatorio;
	
	private String gerenciaRegional;
	
	private String localidadeInicial;
	private String localidadeInicialNome;
	
	private String localidadeFinal;
	private String localidadeFinalNome;
	
	private String categoria;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}


	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getMesAnoReferencial() {
		return mesAnoReferencial;
	}

	public void setMesAnoReferencial(String mesAnoReferencial) {
		this.mesAnoReferencial = mesAnoReferencial;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getLocalidadeFinalNome() {
		return localidadeFinalNome;
	}

	public void setLocalidadeFinalNome(String localidadeFinalNome) {
		this.localidadeFinalNome = localidadeFinalNome;
	}

	public String getLocalidadeInicialNome() {
		return localidadeInicialNome;
	}

	public void setLocalidadeInicialNome(String localidadeInicialNome) {
		this.localidadeInicialNome = localidadeInicialNome;
	}
	
	
}
