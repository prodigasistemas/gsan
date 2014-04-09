package gcom.gui.operacional;

import java.util.Date;

import org.apache.struts.action.ActionForm;



public class InserirDivisaoEsgotoActionForm extends ActionForm {
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String descricao;
    private String unidadeOrganizacional;
    private String unidadeOrganizacionalDescricao;
    private String unidadeOrganizacionalId;
    private Short indicadorUso;
    private Date ultimaAlteracao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String getUnidadeOrganizacionalDescricao() {
		return unidadeOrganizacionalDescricao;
	}

	public void setUnidadeOrganizacionalDescricao(
			String unidadeOrganizacionalDescricao) {
		this.unidadeOrganizacionalDescricao = unidadeOrganizacionalDescricao;
	}

	public String getUnidadeOrganizacionalId() {
		return unidadeOrganizacionalId;
	}

	public void setUnidadeOrganizacionalId(String unidadeOrganizacionalId) {
		this.unidadeOrganizacionalId = unidadeOrganizacionalId;
	}

   

}
