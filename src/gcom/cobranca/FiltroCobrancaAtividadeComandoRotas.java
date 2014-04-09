package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre 
 * @created 06 de Fevereiro de 2006
 */

public class FiltroCobrancaAtividadeComandoRotas extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     */
    public FiltroCobrancaAtividadeComandoRotas() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAtividadeComandoRotas(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO_ATIVIDADE_COMANDO_ID = "cobrancaAcaoAtividadeComando.id";
    
    /**
     * Description of the Field
     */
    public final static String ROTA_ID = "rota.id";
	
    

    /**
     * Description of the Field
     */
    public final static String COMP_ID_COBRANCA_ACAO_ATIVIDADE_COMANDO_ID = "comp_id.cobrancaAcaoAtividadeComandoId";
    
    public final static String COMP_ID_ROTA_ID = "comp_id.rotaId";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_ACAO_ATIVIDADE_COMANDO = "cobrancaAcaoAtividadeComando";

    
    public final static String ROTA_COBRANCA_CRITERIO = "rota.cobrancaCriterio";
    
}
