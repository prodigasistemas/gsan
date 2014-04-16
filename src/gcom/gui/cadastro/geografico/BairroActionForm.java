package gcom.gui.cadastro.geografico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 28 de Junho de 2004
 */
public class BairroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idMunicipio;

    private String nomeMunicipio;

    private String codigoBairro;

    private String nomeBairro;

    private String codigoBairroPrefeitura;

    private String indicadorUso;

    /**
     * Gets the codigoBairro attribute of the BairroActionForm object
     * 
     * @return The codigoBairro value
     */
    public String getCodigoBairro() {
        return codigoBairro;
    }

    /**
     * Sets the codigoBairro attribute of the BairroActionForm object
     * 
     * @param codigoBairro
     *            The new codigoBairro value
     */
    public void setCodigoBairro(String codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    /**
     * Gets the codigoBairroPrefeitura attribute of the BairroActionForm object
     * 
     * @return The codigoBairroPrefeitura value
     */
    public String getCodigoBairroPrefeitura() {
        return codigoBairroPrefeitura;
    }

    /**
     * Sets the codigoBairroPrefeitura attribute of the BairroActionForm object
     * 
     * @param codigoBairroPrefeitura
     *            The new codigoBairroPrefeitura value
     */
    public void setCodigoBairroPrefeitura(String codigoBairroPrefeitura) {
        this.codigoBairroPrefeitura = codigoBairroPrefeitura;
    }

    /**
     * Gets the idMunicipio attribute of the BairroActionForm object
     * 
     * @return The idMunicipio value
     */
    public String getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Sets the idMunicipio attribute of the BairroActionForm object
     * 
     * @param idMunicipio
     *            The new idMunicipio value
     */
    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * Gets the nomeBairro attribute of the BairroActionForm object
     * 
     * @return The nomeBairro value
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * Sets the nomeBairro attribute of the BairroActionForm object
     * 
     * @param nomeBairro
     *            The new nomeBairro value
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * Gets the nomeMunicipio attribute of the BairroActionForm object
     * 
     * @return The nomeMunicipio value
     */
    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    /**
     * Sets the nomeMunicipio attribute of the BairroActionForm object
     * 
     * @param nomeMunicipio
     *            The new nomeMunicipio value
     */
    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }



    public String getIndicadorUso() {
        return indicadorUso;
    }

    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

}
