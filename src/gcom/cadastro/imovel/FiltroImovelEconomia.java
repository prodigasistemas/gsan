package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 11 de Agosto de 2005
 */
public class FiltroImovelEconomia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroImovelEconomia() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelEconomia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovelSubcategoria.comp_id.imovel.id";

    public final static String IMOVEL = "imovelSubcategoria.comp_id.imovel";

    /**
     * Description of the Field
     */
    public final static String MUNICIPIO_ID = "imovelSubcategoria.comp_id.imovel.setorComercial.municipio.id";

    /**
     * Description of the Field
     */
    public final static String IPTU = "numeroIptu";

    /**
     * Description of the Field
     */
    public final static String NUMERO_CELPE = "numeroCelpe";

    /**
     * Description of the Field
     */
    public final static String AREA_CONSTRUIDA_FAIXA = "areaConstruidaFaixa";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_SUB_CATEGORIA = "imovelSubcategoria";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_CATEGORIA = "imovelSubcategoria.comp_id.subcategoria.categoria";

    
    /**
     * Description of the Field
     */
    public final static String SUB_CATEGORIA_ID = "imovelSubcategoria.comp_id.subcategoria.id";
    
    public final static String CLIENTE_IMOVEL_ECONOMIA = "clienteImovelEconomias";
}
