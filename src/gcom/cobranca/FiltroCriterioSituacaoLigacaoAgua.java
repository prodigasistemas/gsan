package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * Filtro da classe CriterioSituacaoLigacaoAgua
 * @author Francisco do Nascimento
 * @date 06/06/2008
 */
public class FiltroCriterioSituacaoLigacaoAgua extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroCriterioSituacaoLigacaoAgua
     */
    public FiltroCriterioSituacaoLigacaoAgua() {
    }

    /**
     * Construtor da classe FiltroCriterioSituacaoLigacaoAgua
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCriterioSituacaoLigacaoAgua(String campoOrderBy) {
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
    
    public final static String LIGACAO_AGUA_SITUACAO = "comp_id.ligacaoAguaSituacao";
    
    public final static String LIGACAO_AGUA_SITUACAO_ID = "comp_id.ligacaoAguaSituacao.id";

}
