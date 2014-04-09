package gcom.gui.cadastro.geografico;

import org.apache.struts.action.ActionForm;



/**
 * [UC0001] INSERIR MUNICIPIO
 * 
 * @author Kassia Albuquerque
 * @created 20/12/2006
 */


public class InserirFeriadoMunicipioPopupActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String data;
	private String descricao;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
    
}
