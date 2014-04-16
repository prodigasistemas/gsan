package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0501]Filtrar Sistema Alteração Historico 
 *
 * @author Kássia Albuquerque
 * @date 27/11/2006
 */


public class FiltrarSistemaAlteracaoHistoricoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String idFuncionalidade;
private String descricaoFuncionalidade;
private String dataAlteracao;
private String tituloAlteracao;


public String getDataAlteracao() {
	return dataAlteracao;
}
public void setDataAlteracao(String dataAlteracao) {
	this.dataAlteracao = dataAlteracao;
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
