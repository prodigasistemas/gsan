package gcom.arrecadacao.pagamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroPagamentoCartaoDebito extends Filtro implements
		Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroPagamentoCartaoDebito() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroPagamentoCartaoDebito(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO_CARTAO_DEBITO = "numeroCartaoDebito";
    
    public final static String INDICADOR_CONFIRMADO_OPERADORA = "indicadorConfirmadoOperadora";
    
    public final static String VALOR_PAGAMENTO = "valorPagamento";
}
