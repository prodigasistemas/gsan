package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroParcelamentoPagamentoCartaoCredito extends Filtro implements
		Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoPagamentoCartaoCredito() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoPagamentoCartaoCredito(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ID_PARCELAMENTO = "parcelamento.id";
    
    public final static String CLIENTE = "cliente";
    
    public final static String PARCELAMENTO = "parcelamento";
    
    public final static String NUMERO_CARTAO_CREDITO = "numeroCartaoCredito";
    
    public final static String INDICADOR_CONFIRMADO_OPERADORA = "indicadorConfirmadoOperadora";
    
    public final static String VALOR_PARCELADO = "valorParcelado";
}
