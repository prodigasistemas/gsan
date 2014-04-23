package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * Filtra Avisos Deducoes
 * 
 * @author thiago toscano
 */
public class FiltroAvisoAcerto extends Filtro {
	
	private static final long serialVersionUID = 1L;
   
    public FiltroAvisoAcerto() {
    }

    public FiltroAvisoAcerto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    // ID
    public final static String ID = "id";
    
    // AVISO BANCARIO
    public final static String AVISO_BANCARIO = "avisoBancario";
    
    // ID DO AVISO BANCARIO 
    public final static String AVISO_BANCARIO_ID = "avisoBancario.id";
    
    // ID DA CONTA BANCARIA
    public final static String CONTA_BANCARIA_ID = "contaBancaria.id";
    
    // CONTA BANCARIA
    public final static String CONTA_BANCARIA = "contaBancaria";


}
