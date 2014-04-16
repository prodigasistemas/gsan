package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Fernando Fontelles Filho
 * @created 21/08/2009
 */
public class PesquisarCategoriaFuncionalidadeActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String id;
    private String descricao;
    private String tipoPesquisa;
    private String idCategoriaSuperior;
    private String descricaoCategoriaSuperior;

    private String idModulo;
    
    private String indicadorUso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoCategoriaSuperior() {
		return descricaoCategoriaSuperior;
	}

	public void setDescricaoCategoriaSuperior(String descricaoCategoriaSuperior) {
		this.descricaoCategoriaSuperior = descricaoCategoriaSuperior;
	}

	public String getIdCategoriaSuperior() {
		return idCategoriaSuperior;
	}

	public void setIdCategoriaSuperior(String idCategoriaSuperior) {
		this.idCategoriaSuperior = idCategoriaSuperior;
	}

	public String getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
