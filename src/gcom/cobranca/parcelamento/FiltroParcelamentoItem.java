package gcom.cobranca.parcelamento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 11 de Agosto de 2005
 */
public class FiltroParcelamentoItem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroImovelEconomia object
     */
    public FiltroParcelamentoItem() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroParcelamentoItem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String PARCELAMENTO = "parcelamento.id";
    
    public final static String CONTA = "contaGeral.id";
    
    public final static String GUIA_PAGAMENTO = "guiaPagamentoGeral.id";
    
    public final static String DEBITO_A_COBRAR = "debitoACobrarGeral.id";
    
    public final static String CREDITO_A_REALIZAR = "creditoARealizarGeral.id";

}
