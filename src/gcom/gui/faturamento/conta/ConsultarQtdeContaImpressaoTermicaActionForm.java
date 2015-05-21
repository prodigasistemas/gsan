package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class ConsultarQtdeContaImpressaoTermicaActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private Integer idGrupoFaturamento;
	private String referencia;
	private Integer idLocalidade;
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
}
