package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 1 de Junho de 2004
 */
public class EconomiaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    private String idImovel;

    private String codigoImovelSubCategoria;

    private String enderecoImovel;
    
    private String matriculaImovel;

    public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	/**
     * Gets the idImovel attribute of the EconomiaActionForm object
     * 
     * @return The idImovel value
     */
    public String getIdImovel() {
        return idImovel;
    }

    /**
     * Sets the idImovel attribute of the EconomiaActionForm object
     * 
     * @param idImovel
     *            The new idImovel value
     */
    public void setIdImovel(String idImovel) {
        this.idImovel = idImovel;
    }

    /**
     * Gets the enderecoImovel attribute of the EconomiaActionForm object
     * 
     * @return The enderecoImovel value
     */
    public String getEnderecoImovel() {
        return enderecoImovel;
    }

    /**
     * Sets the enderecoImovel attribute of the EconomiaActionForm object
     * 
     * @param enderecoImovel
     *            The new enderecoImovel value
     */
    public void setEnderecoImovel(String enderecoImovel) {
        this.enderecoImovel = enderecoImovel;
    }

    /**
     * Gets the codigoImovelSubCategoria attribute of the EconomiaActionForm
     * object
     * 
     * @return The codigoImovelSubCategoria value
     */
    public String getCodigoImovelSubCategoria() {
        return codigoImovelSubCategoria;
    }

    /**
     * Sets the codigoImovelSubCategoria attribute of the EconomiaActionForm
     * object
     * 
     * @param codigoImovelSubCategoria
     *            The new codigoImovelSubCategoria value
     */
    public void setCodigoImovelSubCategoria(String codigoImovelSubCategoria) {
        this.codigoImovelSubCategoria = codigoImovelSubCategoria;
    }

    /**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {

        //idImovel = null;
        //enderecoImovel = null;
        codigoImovelSubCategoria = null;

    }

}
