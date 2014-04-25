package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.action.ActionForm;



/**
 * [UC__?__] DETALHAR SISTEMA ALTERCAO HISTORICO
 * 
 * @author Kassia Albuquerque
 * @created 30/11/2006
 */


public class SistemaHistoricoAlteracaoDetalharPopupActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String idFuncionalidade;
	private String descricaoFuncionalidade;
	private String dataAlteracao;
	private String tituloAlteracao;
	private String descricaoAlteracao;
	
	
	
	public String getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getDescricaoAlteracao() {
		return descricaoAlteracao;
	}
	public void setDescricaoAlteracao(String descricaoAlteracao) {
		this.descricaoAlteracao = descricaoAlteracao;
	}
	public String getDescricaoFuncionalidade() {
		return descricaoFuncionalidade;
	}
	public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
		this.descricaoFuncionalidade = descricaoFuncionalidade;
	}
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	public String getTituloAlteracao() {
		return tituloAlteracao;
	}
	public void setTituloAlteracao(String tituloAlteracao) {
		this.tituloAlteracao = tituloAlteracao;
	}
	
	
    
}
