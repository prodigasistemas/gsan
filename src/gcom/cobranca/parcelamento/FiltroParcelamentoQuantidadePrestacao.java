package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para Parcelamento de Quantidade de Prestação
 *
 * @author Roberta Costa
 * @date 27/03/2006
 */
public class FiltroParcelamentoQuantidadePrestacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String QUANTIDADE_MAXIMA_PRESTACAO = "quantidadeMaximaPrestacao";
    
    public final static String TAXA_JUROS= "taxaJuros";
    
    public final static String PERCENTUAL_MINIMO_ENTRADA = "percentualMinimoEntrada";

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String PARCELAMENTO_QUANTIDADE_REPARCELAMENTO = "parcelamentoQuantidadeReparcelamento.id";
    
    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoQuantidadePrestacao() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoQuantidadePrestacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
