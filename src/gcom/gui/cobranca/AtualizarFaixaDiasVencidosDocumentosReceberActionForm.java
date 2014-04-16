package gcom.gui.cobranca;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Hugo Leonardo
 *
 */
public class AtualizarFaixaDiasVencidosDocumentosReceberActionForm extends ValidatorActionForm {
	
	private Integer valorInicialFaixa;

	private Integer valorFinalFaixa;

	private String descricaoFaixa;
	
	private Short indicadorUso;
	
	private Integer id;

	private static final long serialVersionUID = 1L;

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Returns the descricaoFaixa.
	 */
	public String getDescricaoFaixa() {
		return descricaoFaixa;
	}

	/**
	 * @param descricaoFaixa The descricaoFaixa to set.
	 */
	public void setDescricaoFaixa(String descricaoFaixa) {
		this.descricaoFaixa = descricaoFaixa;
	}

	/**
	 * @return Returns the valorFinalFaixa.
	 */
	public Integer getValorFinalFaixa() {
		return valorFinalFaixa;
	}

	/**
	 * @param valorFinalFaixa The valorFinalFaixa to set.
	 */
	public void setValorFinalFaixa(Integer valorFinalFaixa) {
		this.valorFinalFaixa = valorFinalFaixa;
	}

	/**
	 * @return Returns the valorInicialFaixa.
	 */
	public Integer getValorInicialFaixa() {
		return valorInicialFaixa;
	}

	/**
	 * @param valorInicialFaixa The valorInicialFaixa to set.
	 */
	public void setValorInicialFaixa(Integer valorInicialFaixa) {
		this.valorInicialFaixa = valorInicialFaixa;
	}

}
