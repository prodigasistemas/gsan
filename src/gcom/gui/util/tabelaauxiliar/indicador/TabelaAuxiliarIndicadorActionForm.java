package gcom.gui.util.tabelaauxiliar.indicador;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Rômulo Aurelio
 *
 */
public class TabelaAuxiliarIndicadorActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String descricao;
	
	private String indicadorUso;
	
	private String indicadorNegocio;
	
	private String indicadorBaixaRenda;

	/**
	 * @return Returns the indicadorBaixaRenda.
	 */
	public String getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	/**
	 * @param indicadorBaixaRenda The indicadorBaixaRenda to set.
	 */
	public void setIndicadorBaixaRenda(String indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the indicadorNegocio.
	 */
	public String getIndicadorNegocio() {
		return indicadorNegocio;
	}

	/**
	 * @param indicadorNegocio The indicadorNegocio to set.
	 */
	public void setIndicadorNegocio(String indicadorNegocio) {
		this.indicadorNegocio = indicadorNegocio;
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


}
