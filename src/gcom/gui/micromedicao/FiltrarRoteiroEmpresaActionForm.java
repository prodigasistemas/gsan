package gcom.gui.micromedicao;

import org.apache.struts.action.ActionForm;

/**
 * Form utilizado no Filtrar Roteiro Empresa 
 * 
 * @author Francisco Nascimento
 * @created 10/08/2007
 */
public class FiltrarRoteiroEmpresaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String empresa;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idLeiturista;
	private String nomeLeiturista;	
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String indicadorUso;
	private String atualizar;
	
	public String getAtualizar() {
		return atualizar;
	}
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}
	/**
	 * @return Returns the descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade The descricaoLocalidade to set.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Returns the descricaoSetorComercial.
	 */
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	/**
	 * @param descricaoSetorComercial The descricaoSetorComercial to set.
	 */
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	/**
	 * @return Returns the empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Returns the idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade The idLocalidade to set.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Returns the idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	/**
	 * @param idSetorComercial The idSetorComercial to set.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}
	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	/**
	 * @return Returns the idLeiturista.
	 */
	public String getIdLeiturista() {
		return idLeiturista;
	}
	/**
	 * @param idLeiturista The idLeiturista to set.
	 */
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	/**
	 * @return Returns the nomeLeiturista.
	 */
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	/**
	 * @param nomeLeiturista The nomeLeiturista to set.
	 */
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	
	
}
