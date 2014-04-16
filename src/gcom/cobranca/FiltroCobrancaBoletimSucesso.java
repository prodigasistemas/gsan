package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Mariana Victor 
 * @created 18 de Março de 2011
 */

public class FiltroCobrancaBoletimSucesso extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     */
    public FiltroCobrancaBoletimSucesso() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaBoletimSucesso(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String COBRANCA_BOLETIM_MEDICAO_ID = "cobrancaBoletimMedicao.id";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
	
    

    /**
     * Description of the Field
     */
    public final static String COMP_ID_COBRANCA_BOLETIM_MEDICAO_ID = "comp_id.cobrancaBoletimMedicaoId";
    
    public final static String COMP_ID_IMOVEL_ID = "comp_id.imovelId";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_BOLETIM_MEDICAO = "cobrancaBoletimMedicao";

    
    public final static String IMOVEL = "imovel";
    
}
