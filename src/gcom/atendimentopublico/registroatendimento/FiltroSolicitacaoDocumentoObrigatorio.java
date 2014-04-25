package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Solicitacao Documento Obrigatorio 
 *
 * @author Hugo Leonardo
 * @date 23/08/2010
 */
public class FiltroSolicitacaoDocumentoObrigatorio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
    public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO = "comp_id.solicitacaoTipoEspecificacao";
    
    public final static String MEIO_SOLICITACAO = "comp_id.meioSolicitacao";
    
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_ID = "comp_id.solicitacaoTipoEspecificacao.id";
    
    public final static String MEIO_SOLICITACAO_ID = "comp_id.meioSolicitacao.id";
    
	/**
     * Constructor for the FiltroSolicitacaoDocumentoObrigatorio object
     */
    public FiltroSolicitacaoDocumentoObrigatorio() {
    }

    /**
     * Constructor for the FiltroSolicitacaoDocumentoObrigatorio object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoDocumentoObrigatorio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}
