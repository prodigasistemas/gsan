package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroDistritoOperacional extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroDistritoOperacional(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroDistritoOperacional() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";
    /**
     * Description of the Field
     */
    public final static String ZONAABASTECIMENTO = "zonaAbastecimento.id";
    /**
     * Description of the Field
     */
    public final static String SETORABASTECIMENTO = "setorAbastecimento.id";    
    /**
     * Description of the Field
     */
    public final static String SISTEMAABASTECIMENTO = "setorAbastecimento.sistemaAbastecimento.id";
    
}
