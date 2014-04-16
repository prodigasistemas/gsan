package gcom.operacional;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroProducaoAgua extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroProducaoAgua(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroProducaoAgua() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
	 * Description of the Field
	 */
	public final static String MES_ANO_REFERENCIA = "anoMesReferencia";
    
    /**
     * Description of the Field
     */
    public final static String ID_LOCALIDADE = "localidade.id";
    
    public final static String LOCALIDADE = "localidade";
	
    /**
	 * Description of the Field
	 */
	public final static String VOLUME_PRODUZIDO = "volumeProduzido";
	
}
