package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<>>
 * 
 * @author lms
 * @date 03/08/2006
 */
public class PesquisarServicoTipoAtividadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	String idAtividade;
	String descricaoAtividade;
	String ordemExecucao;
	String method;
	
	public void reset(){
	  this.idAtividade = null;
	  this.descricaoAtividade = null;
	  this.ordemExecucao = null;
	  this.method = null;
	}
	
	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}
	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}
	public String getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}
	public String getOrdemExecucao() {
		return ordemExecucao;
	}
	public void setOrdemExecucao(String ordemExecucao) {
		this.ordemExecucao = ordemExecucao;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
}
