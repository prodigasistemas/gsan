package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * Filtro da classe CriterioSituacaoLigacaoEsgoto
 * @author Francisco do Nascimento
 * @date 06/06/2008
 */
public class FiltroCriterioSituacaoLigacaoEsgoto extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroCriterioSituacaoLigacaoEsgoto
     */
    public FiltroCriterioSituacaoLigacaoEsgoto() {
    }

    /**
     * Construtor da classe FiltroCriterioSituacaoLigacaoEsgoto
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCriterioSituacaoLigacaoEsgoto(String campoOrderBy) {
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
    
    public final static String LIGACAO_ESGOTO_SITUACAO = "comp_id.ligacaoEsgotoSituacao";
    
    public final static String LIGACAO_ESGOTO_SITUACAO_ID = "comp_id.ligacaoEsgotoSituacao.id";

}
