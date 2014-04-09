package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

/**
 * Filtra Avisos Deducoes
 * 
 * @author Rhawi Dantas
 */
public class FiltroAvisoDeducoes extends Filtro {
	
	private static final long serialVersionUID = 1L;
   
    public FiltroAvisoDeducoes() {
    }

    public FiltroAvisoDeducoes(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    public final static String ID = "id";
    
    public final static String DEDUCAO_TIPO_ID = "deducaoTipo.id";
    
    public final static String AVISO_BANCARIO_ID = "avisoBancario.id";
    
    public final static String AVISO_BANCARIO_ARRECADADOR_ID = "avisoBancario.arrecadador.id";
    
    public final static String AVISO_BANCARIO_ARRECADADOR_CODIGO = "avisoBancario.arrecadador.codigoAgente";
    
    public final static String AVISO_BANCARIO_DATA_LANCAMENTO = "avisoBancario.dataLancamento";
    
    public final static String AVISO_BANCARIO_NUMERO_SEQUENCIAL = "avisoBancario.numeroSequencial";
}
