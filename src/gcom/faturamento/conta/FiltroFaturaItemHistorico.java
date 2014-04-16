package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 26/04/2007
 */
public class FiltroFaturaItemHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor de FiltroFatura 
     * 
     */
    public FiltroFaturaItemHistorico() {
    }
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String ANO_MES_REFERENCIA = "fatura.anoMesReferencia";
    
    public final static String CLIENTE_ID = "fatura.cliente.id";
    
    public final static String NUMERO_SEQUENCIA = "itemSequencia";
    
    public final static String FATURA_ID = "fatura.id"; 

    /**
     * Construtor de FiltroFatura 
     * 
     * @param campoOrderBy
     */
    public FiltroFaturaItemHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

