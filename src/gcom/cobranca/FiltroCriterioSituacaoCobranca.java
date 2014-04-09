package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * Filtro da classe CriterioSituacaoCobranca
 * @author Francisco do Nascimento
 * @date 06/06/2008
 */
public class FiltroCriterioSituacaoCobranca extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroCriterioSituacaoCobranca
     */
    public FiltroCriterioSituacaoCobranca() {
    }

    /**
     * Construtor da classe FiltroCriterioSituacaoCobranca
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCriterioSituacaoCobranca(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String COBRANCA_CRITERIO = "comp_id.cobrancaCriterio";
    
    public final static String COBRANCA_CRITERIO_ID = "comp_id.cobrancaCriterio.id";
    
    public final static String COBRANCA_SITUACAO = "comp_id.cobrancaSituacao";
    
    public final static String COBRANCA_SITUACAO_ID = "comp_id.cobrancaSituacao.id";

}
