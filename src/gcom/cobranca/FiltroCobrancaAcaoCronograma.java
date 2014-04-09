package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * 
 * 
 * @author Rafael Santos
 * @created 21/02/2006
 */

public class FiltroCobrancaAcaoCronograma extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAcaoCronograma object
     */
    public FiltroCobrancaAcaoCronograma() {
    }

    /**
     * Constructor for the FiltroCobrancaAcaoCronograma object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAcaoCronograma(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id"; 

    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id"; 
    
    /**
     * Description of the Field
     */
    public final static String ID_COBRANCA_GRUPO_CRONOGRAMA_MES = "cobrancaGrupoCronogramaMes.id"; 
    
    public final static String COBRANCA_ACAO_ORDEM_REALIZACAO = "cobrancaAcao.ordemRealizacao";
    
    public final static String COBRANCA_ACAO_PREDECESSORA = "cobrancaAcao.cobrancaAcaoPredecessora.id";
    
    public final static String COBRANCA_GRUPO_MES = "cobrancaGrupo.mesAnoReferencia";
    
}
