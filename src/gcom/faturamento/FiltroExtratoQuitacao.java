package gcom.faturamento;

import gcom.util.filtro.Filtro;

public class FiltroExtratoQuitacao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroExtratoQuitacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroExtratoQuitacao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ID_IMOVEL = "imovel.id";
    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel";
    
    
    /**
     * Description of the Field
     */
    public final static String ANO_REFERENCIA = "anoReferencia";
    
    

}
