package gcom.spcserasa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroNegativador
 *
 * @author Thiago Toscano 
 * @date 26/12/2007
 */
public class FiltroNegativacaoImoveis extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroNegativador object
     */
    public FiltroNegativacaoImoveis() {
    }

    /**
     * Constructor for the FiltroNegativador object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativacaoImoveis(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String IMOVEL_ID = "imovel.id";

    public final static String NEGATIVACAO_COMANDO_ID = "negativacaoComando.id";

    public final static String NEGATIVADOR_ID = "negativacaoComando.negativador.id";

    public final static String CLIENTE_ID = "negativacaoComando.negativador.cliente.id";

    public final static String CLIENTE = "negativacaoComando.negativador.cliente";

    public final static String NEGATIVADOR = "negativacaoComando.negativador";

    public final static String INDICADOR_EXCLUIDO = "indicadorExcluido";
}
