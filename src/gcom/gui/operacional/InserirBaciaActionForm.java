package gcom.gui.operacional;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 21/05/2008
 */
public class InserirBaciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	Integer id;
	
	String descricao;
	
	String sistemaEsgoto;
	
	String indicadorUso;
	
	
	public String getIndicadorUso() {
		return indicadorUso;
	}
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	public String getSistemaEsgoto(){
		return sistemaEsgoto;
	}
	public void setSistemaEsgoto(String sistemaEsgoto){
		this.sistemaEsgoto = sistemaEsgoto;
	}
	
	public  String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	

	
}
