package gcom.faturamento;

import gcom.util.filtro.Filtro;


/**
 * 
 * Filtro de Movimento Conta Pre Faturada 
 *
 * @author bruno
 * @date 07/07/2009
 */
public class FiltroMovimentoContaCategoriaConsumoFaixa extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMovimentoContaCategoriaConsumoFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroAreaConstruidaFaxia
     */
    public FiltroMovimentoContaCategoriaConsumoFaixa() {
    }

    /**
     * Description of the Field
     */
    public final static String MOVIMENTO_CONTA_PREFATURADA_ID = "movimentoContaPrefaturadaCategoria.comp_id.movimentoContaPrefaturada.id";
    public final static String CONTA_ID = "movimentoContaPrefaturadaCategoria.comp_id.movimentoContaPrefaturada.conta.id";
    public final static String CATEGORIA_ID = "movimentoContaPrefaturadaCategoria.comp_id.categoria.id";
    public final static String SUBCATEGORIA_ID = "movimentoContaPrefaturadaCategoria.comp_id.subcategoria.id";
    public final static String LIMITE_INICIAL_CONSUMO_FAIXA = "limiteInicialConsumoNaFaixa";
    public final static String LIMITE_FINAL_CONSUMO_FAIXA = "limiteFinalConsumoNaFaixa";
}
