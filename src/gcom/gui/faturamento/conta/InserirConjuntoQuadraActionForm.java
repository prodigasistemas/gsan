package gcom.gui.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class InserirConjuntoQuadraActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidade;
    private String localidade;

    private String idSetorComercial;
    private String setorComercial;
    
    private String[] idQuadras;

    
    public void limpar() {
    	this.idQuadras = null;
    }
    
	/**
	 * @return Retorna o campo idQuadras.
	 */
	public String[] getIdQuadras() {
		return idQuadras;
	}

	/**
	 * @param idQuadras O idQuadras a ser setado.
	 */
	public void setIdQuadras(String[] idQuadras) {
		this.idQuadras = idQuadras;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo setorComercial.
	 */
	public String getSetorComercial() {
		return setorComercial;
	}

	/**
	 * @param setorComercial O setorComercial a ser setado.
	 */
	public void setSetorComercial(String setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
    
}
