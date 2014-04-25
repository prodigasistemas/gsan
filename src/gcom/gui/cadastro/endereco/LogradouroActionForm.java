package gcom.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Sávio Luiz
 * @created 19 de Julho de 2005
 */
public class LogradouroActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String nome;
    
    private String nomePopular;

    private Integer idTitulo;

    private Integer idTipo;

    private String idMunicipio;

    private String nomeMunicipio;

    private String codigoBairro;
    
    private String nomeBairro;
    
    private String codigoCEP;
    
    private String descricaoCEP;

    private String indicadorUso;
    
    private String colecaoBairro;
    
    private String colecaoCep;
    
    

    public String getColecaoBairro() {
		return colecaoBairro;
	}

	public void setColecaoBairro(String colecaoBairro) {
		this.colecaoBairro = colecaoBairro;
	}

	public String getColecaoCep() {
		return colecaoCep;
	}

	public void setColecaoCep(String colecaoCep) {
		this.colecaoCep = colecaoCep;
	}

	/**
     * Gets the codigoBairro attribute of the LogradouroActionForm object
     * 
     * @return The codigoBairro value
     */
    public String getCodigoBairro() {
        return codigoBairro;
    }

    /**
     * Sets the codigoBairro attribute of the LogradouroActionForm object
     * 
     * @param codigoBairro
     *            The new codigoBairro value
     */
    public void setCodigoBairro(String codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    /**
     * Gets the idMunicipio attribute of the LogradouroActionForm object
     * 
     * @return The idMunicipio value
     */
    public String getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Sets the idMunicipio attribute of the LogradouroActionForm object
     * 
     * @param idMunicipio
     *            The new idMunicipio value
     */
    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    /**
     * Gets the idTipo attribute of the LogradouroActionForm object
     * 
     * @return The idTipo value
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * Sets the idTipo attribute of the LogradouroActionForm object
     * 
     * @param idTipo
     *            The new idTipo value
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * Gets the idTitulo attribute of the LogradouroActionForm object
     * 
     * @return The idTitulo value
     */
    public Integer getIdTitulo() {
        return idTitulo;
    }

    /**
     * Sets the idTitulo attribute of the LogradouroActionForm object
     * 
     * @param idTitulo
     *            The new idTitulo value
     */
    public void setIdTitulo(Integer idTitulo) {
        this.idTitulo = idTitulo;
    }

    /**
     * Gets the nome attribute of the LogradouroActionForm object
     * 
     * @return The nome value
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome attribute of the LogradouroActionForm object
     * 
     * @param nome
     *            The new nome value
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the nomeBairro attribute of the LogradouroActionForm object
     * 
     * @return The nomeBairro value
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * Sets the nomeBairro attribute of the LogradouroActionForm object
     * 
     * @param nomeBairro
     *            The new nomeBairro value
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * Gets the nomeMunicipio attribute of the LogradouroActionForm object
     * 
     * @return The nomeMunicipio value
     */
    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    /**
     * Sets the nomeMunicipio attribute of the LogradouroActionForm object
     * 
     * @param nomeMunicipio
     *            The new nomeMunicipio value
     */
    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    /**
     * Gets the indicadorUso attribute of the LogradouroActionForm object
     * 
     * @return The indicadorUso value
     */
    public String getIndicadorUso() {
        return indicadorUso;
    }

    /**
     * Sets the indicadorUso attribute of the LogradouroActionForm object
     * 
     * @param indicadorUso
     *            The new indicadorUso value
     */
    public void setIndicadorUso(String indicadorUso) {
        this.indicadorUso = indicadorUso;
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
       /* nome = null;
        idTitulo = null;
        idTipo = null;
        idMunicipio = null;
        nomeMunicipio = null;
        codigoBairro = null;
        nomeBairro = null;
        indicadorUso = null; */
    }

	public String getNomePopular() {
		return nomePopular;
	}

	public void setNomePopular(String nomePopular) {
		this.nomePopular = nomePopular;
	}

	public String getCodigoCEP() {
		return codigoCEP;
	}

	public void setCodigoCEP(String codigoCEP) {
		this.codigoCEP = codigoCEP;
	}

	public String getDescricaoCEP() {
		return descricaoCEP;
	}

	public void setDescricaoCEP(String descricaoCEP) {
		this.descricaoCEP = descricaoCEP;
	}

}
