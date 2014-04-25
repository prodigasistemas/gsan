package gcom.cadastro.unidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeOrganizacionalMunicipio 
 *
 * @author Arthur Carvalho
 * @date 08/04/2010
 */
public class FiltroUnidadeOrganizacionalMunicipio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroUnidadeOrganizacionalMunicipio() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUnidadeOrganizacionalMunicipio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Unidade organizacional municipio
     */
    public final static String ID = "id";
    
    /**
     * Id do Municipio
     */
    public final static String ID_MUNICIPIO = "idMunicipio";
    
    /**
     * Id da Unidade Repavimentadora
     */
    public final static String ID_UNIDADE_REPAVIMENTADORA = "idUnidadeRepavimentadora";
    
    /**
     * Data desvinculacao
     * 
     */
    public final static String DATA_DESVINCULACAO = "dataDesvinculacao";
    
    
    
}
