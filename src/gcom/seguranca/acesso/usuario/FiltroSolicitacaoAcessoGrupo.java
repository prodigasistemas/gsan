package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Solicitacao Acesso Grupo 
 *
 * @author Hugo Leonardo
 * @date 17/11/2010
 */
public class FiltroSolicitacaoAcessoGrupo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
    public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_ACESSO = "comp_id.solicitacaoAcesso";
    
    public final static String GRUPO = "comp_id.grupo";
    
    public final static String SOLICITACAO_ACESSO_ID = "comp_id.solicitacaoAcesso.id";
    
    public final static String GRUPO_ID = "comp_id.grupo.id";
    
	/**
     * Constructor for the FiltroSolicitacaoAcessoGrupo object
     */
    public FiltroSolicitacaoAcessoGrupo() {
    }

    /**
     * Constructor for the FiltroSolicitacaoAcessoGrupo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoAcessoGrupo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}
