package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 26/04/2007
 */
public class FiltroFatura extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor de FiltroFatura 
     * 
     */
    public FiltroFatura() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA = "anoMesReferencia";
    
    /**
     * Description of the Field
     */
    public final static String CODIGO_QUALIFICA = "codigoQualifica";
    
    public final static String CLIENTE_ID = "cliente.id";

    /**
     * Construtor de FiltroFatura 
     * 
     * @param campoOrderBy
     */
    public FiltroFatura(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

