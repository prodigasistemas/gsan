package gcom.faturamento;

import gcom.util.filtro.Filtro;


/**
 * 
 * Filtro de Movimento Conta Pre Faturada 
 *
 * @author bruno
 * @date 07/07/2009
 */
public class FiltroMovimentoContaImpostoDeduzido extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMovimentoContaImpostoDeduzido(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroMovimentoContaImpostoDeduzido() {
    }

    /**
     * Description of the Field
     */
    public final static String MOVIMENTO_CONTA_PREFATURADA_ID = "movimentoContaPrefaturada.id";
}
