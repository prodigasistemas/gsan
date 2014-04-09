package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro para Parcelamento de Quantidade de Reparcelamento
 *
 * @author Roberta Costa
 * @date 27/03/2006
 */
public class FiltroParcelamentoQuantidadeReparcelamento extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String QUANTIDADE_MAXIMA_REPARCELAMENTO = "quantidadeMaximaReparcelamento";
    
    public final static String VALOR_MINIMO_PRESTACAO = "valorMinimoPrestacao";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String PARCELAMENTO_PERFIL = "parcelamentoPerfil.id";
    
    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoQuantidadeReparcelamento() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoQuantidadeReparcelamento(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
