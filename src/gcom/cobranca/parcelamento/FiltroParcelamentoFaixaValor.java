package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 25/10/2006
 */
public class FiltroParcelamentoFaixaValor extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    
    public final static String PARCELAMENTO_QUANTIDADE_PRESTACAO = "parcelamentoQuantidadePrestacao.id";
    
    
    public final static String VALOR_FAIXA = "valorFaixa";
    
    
    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoFaixaValor() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoFaixaValor(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
