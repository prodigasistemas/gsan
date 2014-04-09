package gcom.cobranca;

import gcom.util.filtro.Filtro;

/**
 * [UC0214] - Efetuar Parcelamento de Débitos
 * Filtro para Resolução de Diretoria
 * @author  Roberta Costa
 * @created 17/02/2006 
 */
public class FiltroUnidadeNegocioTestemunha extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO = "numeroResolucaoDiretoria";

    /**
     * Description of the Field
     */
    public final static String DATA_RELACAO_INICIO = "dataInicioRelacao";

    /**
     * Description of the Field
     */
    public final static String DATA_RELACAO_FIM = "dataFimRelacao";
    
    /**
     * Description of the Field
     */
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio";
    
    /**
     * Description of the Field
     */
    public final static String UNIDADE_NEGOCIO_ID = "unidadeNegocio.id";
    
    /**
     * Description of the Field
     */
    public final static String USUARIO = "usuario";
    
    /**
     * Description of the Field
     */
    public final static String USUARIO_ID = "usuario.id";
    
    /**
     * Description of the Field
     */
    public final static String USUARIO_LOGIN = "usuario.login";

    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroUnidadeNegocioTestemunha() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroUnidadeNegocioTestemunha(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
