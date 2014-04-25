package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * @author Mariana Victor 
 * @created 18 de Março de 2011
 */

public class FiltroCobrancaBoletimDesconto extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     */
    public FiltroCobrancaBoletimDesconto() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividadeComandoRotas object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaBoletimDesconto(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String COBRANCA_BOLETIM_MEDICAO_ID = "cobrancaBoletimMedicao.id";
    
    /**
     * Description of the Field
     */
    public final static String ORDEM_SERVICO_ID = "ordemServico.id";
	
    

    /**
     * Description of the Field
     */
    public final static String COMP_ID_COBRANCA_BOLETIM_MEDICAO_ID = "comp_id.cobrancaBoletimMedicaoId";
    
    public final static String COMP_ID_ORDEM_SERVICO_ID = "comp_id.ordemServicoId";
    
    /**
     * Description of the Field
     */
    public final static String COBRANCA_BOLETIM_MEDICAO = "cobrancaBoletimMedicao";

    
    public final static String ORDEM_SERVICO = "ordemServico";
    
}
