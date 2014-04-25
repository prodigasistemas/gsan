package gcom.gui.cadastro.imovel;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * form responsável pelo fim da relação entre cliente e imóvel
 * 
 * @author Sávio Luiz
 */
public class FimRelacaoClienteImovelActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String dataTerminoRelacao;

    private String idMotivo;

    /**
     * Gets the dataTerminoRelacao attribute of the
     * FimRelacaoClienteImovelActionForm object
     * 
     * @return The dataTerminoRelacao value
     */
    public String getDataTerminoRelacao() {
        return dataTerminoRelacao;
    }

    /**
     * Sets the dataTerminoRelacao attribute of the
     * FimRelacaoClienteImovelActionForm object
     * 
     * @param dataTerminoRelacao
     *            The new dataTerminoRelacao value
     */
    public void setDataTerminoRelacao(String dataTerminoRelacao) {
        this.dataTerminoRelacao = dataTerminoRelacao;
    }

    /**
     * Gets the idMotivo attribute of the FimRelacaoClienteImovelActionForm
     * object
     * 
     * @return The idMotivo value
     */
    public String getIdMotivo() {
        return idMotivo;
    }

    /**
     * Sets the idMotivo attribute of the FimRelacaoClienteImovelActionForm
     * object
     * 
     * @param idMotivo
     *            The new idMotivo value
     */
    public void setIdMotivo(String idMotivo) {
        this.idMotivo = idMotivo;
    }

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
//        dataTerminoRelacao = null;
//        idMotivo = null;

    }

}
