package gcom.gui.relatorio.micromedicao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

import gcom.gui.GcomAction;

public class RelatorioLeiturasRealizadasActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String idGrupoFaturamento;
	
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	
	
	

	
}
