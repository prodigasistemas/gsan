package gcom.gui.cobranca;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author Hugo Leonardo
 *
 */
public class InserirFaixaDiasVencidosDocumentosReceberActionForm extends ValidatorActionForm {
	
	private String valorInicialFaixa;

	private String valorFinalFaixa;

	private String descricaoFaixa;
	
	private String indicadorUso;
	
	private String id;

	private static final long serialVersionUID = 1L;

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
	public String getValorFinalFaixa() {
		return valorFinalFaixa;
	}

	/**
	 * @param valorFinalFaixa The valorFinalFaixa to set.
	 */
	public void setValorFinalFaixa(String valorFinalFaixa) {
		this.valorFinalFaixa = valorFinalFaixa;
	}

	/**
	 * @return Returns the valorInicialFaixa.
	 */
	public String getValorInicialFaixa() {
		return valorInicialFaixa;
	}

	/**
	 * @param valorInicialFaixa The valorInicialFaixa to set.
	 */
	public void setValorInicialFaixa(String valorInicialFaixa) {
		this.valorInicialFaixa = valorInicialFaixa;
	}

}
