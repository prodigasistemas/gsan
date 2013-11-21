package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0777] Pesquisar Empresa
 * 
 * @see gcom.gui.cadastro.ExibirPesquisarEmpresaAction
 * @see gcom.gui.cadastro.PesquisarEmpresaAction
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class PesquisarEmpresaActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String descricaoEmpresa;
	private String tipoPesquisa;

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
