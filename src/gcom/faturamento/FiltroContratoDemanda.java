package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroContratoDemanda extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroContratoDemanda(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroContratoDemanda() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String MUMEROCONTRATO = "numeroContrato";
    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel.id";
    /**
     * Description of the Field
     */
    public final static String DATACONTRATOINICIO = "dataContratoInicio";
    /**
     * Description of the Field
     */
    public final static String DATACONTRATOFIM = "dataContratoFim";
    /**
     * Description of the Field
     */
    public final static String DATACONTRATOENCERRAMENTO = "dataContratoEncerrado";
    
}
