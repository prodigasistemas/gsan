package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro Negativador Retorno Motivo
 *
 * @author Thiago Vieira
 * @date 03/01/2008
 */
public class FiltroNegativadorRetornoMotivo extends Filtro implements Serializable {
	
	private final static long serialVersionUID = 1L;
	
	public final static String ID = "id";

    /** nullable persistent field */
	public final static String DESCRICAO_RETORNO_CODIGO = "descricaoRetornocodigo";

    /** persistent field */
	public final static String INDICADOR_USO = "indicadorUso";

    /** persistent field */
	public final static String INDICADOR_REGISTRO_ACEITO = "indicadorRegistroAceito";

    /** persistent field */
	public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** persistent field */
	public final static String NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR = "negativador.id";

    /** persistent field */
	public final static String NEGATIVADOR_MOVIMENTO_REG_RET_MOT = "negativadorMovimentoRegRetMot";
    
	public final static String CODIGO_RETORNO_MOTIVO = "codigoRetornoMotivo";

	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativadorRetornoMotivo() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativadorRetornoMotivo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    

}
