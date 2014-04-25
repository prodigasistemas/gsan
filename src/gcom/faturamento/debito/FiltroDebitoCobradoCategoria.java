package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

/**
 * filtro de debito cobrado categoria
 * 
 * @author  Raphael Rossiter
 * @created 13/06/2007
 */
public class FiltroDebitoCobradoCategoria extends Filtro {
	
	private static final long serialVersionUID = 1L;
	
	public FiltroDebitoCobradoCategoria() {
    }

    public FiltroDebitoCobradoCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    public final static String DEBITO_COBRADO_ID = "debitoCobrado.id";
    public final static String CONTA = "debitoCobrado.conta";
    public final static String DEBITO_COBRADO = "debitoCobrado";
    public final static String CONTA_ID = "debitoCobrado.conta.id";

}
