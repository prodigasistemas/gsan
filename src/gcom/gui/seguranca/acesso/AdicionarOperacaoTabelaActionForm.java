package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;



/**
 * Form responsável por manipular os dados para adicionar uma tabela na operação 
 *
 * @author Pedro Alexandre
 * @date 05/05/2006
 */
public class AdicionarOperacaoTabelaActionForm extends	ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String tabela;

	private String idTabela;
	
	private String descricaoTabela;

	private String comp_id;

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public String getDescricaoTabela() {
		return descricaoTabela;
	}

	public void setDescricaoTabela(String descricaoTabela) {
		this.descricaoTabela = descricaoTabela;
	}

	public String getIdTabela() {
		return idTabela;
	}

	public void setIdTabela(String idTabela) {
		this.idTabela = idTabela;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	
	
}
