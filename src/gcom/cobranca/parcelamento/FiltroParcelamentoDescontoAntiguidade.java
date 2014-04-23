package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Descrição da classe 
 *
 * @author Roberta Costa
 * @date 23/03/2006
 */
public class FiltroParcelamentoDescontoAntiguidade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String QUANTIDADE_MINIMA_MESES_DEBITO = "quantidadeMinimaMesesDebito";
    
    public final static String PERCENTUAL_DESCONTO_SEM_RESTABELECIMENTO = "percentualDescontoSemRestabelecimento";
    
    public final static String PERCENTUAL_DESCONTO_COM_RESTABELECIMENTO = "percentualDescontoComRestabelecimento";
    
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String PARCELAMENTO_PERFIL = "parcelamentoPerfil.id";
    
    public final static String PERCENTUAL_DESCONTO_ATIVO = "percentualDescontoAtivo";
    
    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoDescontoAntiguidade() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoDescontoAntiguidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
