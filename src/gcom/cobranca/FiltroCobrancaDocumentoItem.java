package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Pedro Alexandre
 * @created 08 de Fevereiro de 2006
 */

public class FiltroCobrancaDocumentoItem extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaDocumentoItem object
     */
    public FiltroCobrancaDocumentoItem() {
    }

    /**
     * Constructor for the FiltroCobrancaDocumentoItem object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaDocumentoItem(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_DOCUMENTO_ID = "cobrancaDocumento.id";
    
    /**
     * Description of the Field
     */
    public final static String DEBITO_A_COBRAR_GERAL_ID = "debitoACobrarGeral.id";

    /**
     * Description of the Field
     */
    public final static String CONTA_GERAL_ID = "contaGeral.id";
    /**
     * Description of the Field
     */
    public final static String PRESTACAO_CONTRATO_PARCELAMENTO_ID = "prestacaoContratoParcelamento.contratoParcelamento.id";
	
}
