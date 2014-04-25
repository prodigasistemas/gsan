package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEmpresaCobrancaContaPagamentos extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmpresaCobrancaContaPagamentos(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEmpresaCobrancaContaPagamentos() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String PAGAMENTO = "pagamento";
    
    /**
     * Description of the Field
     */
    public final static String PAGAMENTO_ID = "pagamento.id";


    /**
     * Description of the Field
     */
    public final static String DEBITO_TIPO = "debitoTipo";
    
    /**
     * Description of the Field
     */
    public final static String DEBITO_TIPO_ID = "debitoTipo.id";

}
