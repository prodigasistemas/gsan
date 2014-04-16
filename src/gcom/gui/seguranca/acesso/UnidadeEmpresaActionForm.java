package gcom.gui.seguranca.acesso;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class UnidadeEmpresaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String codigoUnidade;

    private String nomeUnidade;

    private String siglaUnidade;

    private String nivelHiearquia;

    private String idUnidadeSuperior;
    
    private String descricaoUnidadeSuperior;


 
	/**
	 * @return Retorna o campo codigoUnidade.
	 */
	public String getCodigoUnidade() {
		return codigoUnidade;
	}

	/**
	 * @param codigoUnidade O codigoUnidade a ser setado.
	 */
	public void setCodigoUnidade(String codigoUnidade) {
		this.codigoUnidade = codigoUnidade;
	}

	/**
	 * @return Retorna o campo nivelHiearquia.
	 */
	public String getNivelHiearquia() {
		return nivelHiearquia;
	}

	/**
	 * @param nivelHiearquia O nivelHiearquia a ser setado.
	 */
	public void setNivelHiearquia(String nivelHiearquia) {
		this.nivelHiearquia = nivelHiearquia;
	}

	/**
	 * @return Retorna o campo nomeUnidade.
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * @param nomeUnidade O nomeUnidade a ser setado.
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	/**
	 * @return Retorna o campo siglaUnidade.
	 */
	public String getSiglaUnidade() {
		return siglaUnidade;
	}

	/**
	 * @param siglaUnidade O siglaUnidade a ser setado.
	 */
	public void setSiglaUnidade(String siglaUnidade) {
		this.siglaUnidade = siglaUnidade;
	}

	/**
	 * @return Retorna o campo unidadeSuperior.
	 */
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	/**
	 * @param unidadeSuperior O unidadeSuperior a ser setado.
	 */
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}

	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
	}

}
