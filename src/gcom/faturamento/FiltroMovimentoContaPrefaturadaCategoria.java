package gcom.faturamento;

import gcom.util.filtro.Filtro;


/**
 * 
 * Filtro de Movimento Conta Pre Faturada 
 *
 * @author bruno
 * @date 07/07/2009
 */
public class FiltroMovimentoContaPrefaturadaCategoria extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMovimentoContaPrefaturadaCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroMovimentoContaPrefaturadaCategoria() {
    }

    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "conta.id";
    public final static String MOVIMENTO_CONTA_PREFATURADA_ID = "comp_id.movimentoContaPrefaturada.id";
    public final static String ID_CATEGORIA = "comp_id.categoria.id";
}
