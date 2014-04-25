package gcom.gui.cadastro.geografico;

import org.apache.struts.action.ActionForm;

/**
 * Form utilizado no Inserir Bairro e no Atualizar Bairro
 * 
 * @author Vivianne Sousa
 * @created 19/12/2006
 */
public class AdicionarAreaBairroActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String municipioId;
	private String municipioDescricao;
	private String bairroCodigo;
	private String bairroDescricao;
	private String areaBairroNome;
	private String distritoOperacionalID;
	private String distritoOperacionalDescricao;
	
	public String getAreaBairroNome() {
		return areaBairroNome;
	}
	public void setAreaBairroNome(String areaBairroNome) {
		this.areaBairroNome = areaBairroNome;
	}
	public String getBairroCodigo() {
		return bairroCodigo;
	}
	public void setBairroCodigo(String bairroCodigo) {
		this.bairroCodigo = bairroCodigo;
	}
	public String getBairroDescricao() {
		return bairroDescricao;
	}
	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}
	public String getDistritoOperacionalDescricao() {
		return distritoOperacionalDescricao;
	}
	public void setDistritoOperacionalDescricao(String distritoOperacionalDescricao) {
		this.distritoOperacionalDescricao = distritoOperacionalDescricao;
	}
	public String getDistritoOperacionalID() {
		return distritoOperacionalID;
	}
	public void setDistritoOperacionalID(String distritoOperacionalID) {
		this.distritoOperacionalID = distritoOperacionalID;
	}
	public String getMunicipioDescricao() {
		return municipioDescricao;
	}
	public void setMunicipioDescricao(String municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
	}
	public String getMunicipioId() {
		return municipioId;
	}
	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}
	
	
	
	
	
}
