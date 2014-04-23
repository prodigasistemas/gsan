package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Localidade Especificacao Unidade
 *
 * @author Hugo Leonardo
 * @date 29/11/2010
 */
public class FiltroLocalidadeEspecificacaoUnidade extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
	public final static String ID = "id";
	
	public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "comp_id.solicitacaoTipoEspecificacao";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_ID = "comp_id.solicitacaoTipoEspecificacao.id";
    
    public final static String LOCALIDADE = "comp_id.localidade";
    
    public final static String LOCALIDADE_ID = "comp_id.localidade.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "comp_id.unidadeOrganizacional";
    
    public final static String INDICADOR_TRAMITE_UNIDADE_ORGANIZACIONAL = "comp_id.unidadeOrganizacional.indicadorTramite";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "comp_id.unidadeOrganizacional.id";

	/**
     * Constructor for the FiltroLocalidadeEspecificacaoUnidade object
     */
    public FiltroLocalidadeEspecificacaoUnidade() {
    }

    /**
     * Constructor for the FiltroLocalidadeEspecificacaoUnidade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroLocalidadeEspecificacaoUnidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}
