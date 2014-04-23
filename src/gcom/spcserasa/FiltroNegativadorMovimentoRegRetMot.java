package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativadorMovimentoReg
 *
 * @author Yara Taciane 
 * @date 10/01/2008
 */
public class FiltroNegativadorMovimentoRegRetMot extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorMovimentoRegRetMot() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorMovimentoRegRetMot(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String NEGATIVADOR_MOVIMENTO_REG_ID = "negativadorMovimentoReg.id";
    
    public final static String NEGATIVADOR_MOVIMENTO_ID = "negativadorMovimento.id";
    
    public final static String NEGATIVADOR_ID = "negativadorMovimento.negativador.id";
    
    public final static String NUMERO_REGISTRO = "numeroRegistro";
    
    public final static String NEGATIVADOR_RETORNO_MOTIVO = "negativadorRetornoMotivo";
    
    public final static String NEGATIVADOR_RETORNO_MOTIVO_ID = "negativadorRetornoMotivo.id";

}
