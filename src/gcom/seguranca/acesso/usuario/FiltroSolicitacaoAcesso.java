package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Solicitacao Acesso
 *
 * @author Hugo Leonardo
 * @date 12/11/2010
 */
public class FiltroSolicitacaoAcesso extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

    /**
     * Description of the Field
     */
	public final static String ID = "id";
	
	public final static String CODIGO = "comp_id";
    
    public final static String SOLICITACAO_ACESSO = "comp_id.solicitacaoAcesso";
    
    public final static String GRUPO = "comp_id.grupo";
    
    public final static String SOLICITACAO_ACESSO_ID = "comp_id.solicitacaoAcesso.id";
    
    public final static String GRUPO_ID = "comp_id.grupo.id";
    
    public final static String LOGIN = "login";
    
    public final static String FUNCIONARIO_SOLICITANTE = "funcionarioSolicitante";
    
    public final static String FUNCIONARIO_SOLICITANTE_ID = "funcionarioSolicitante.id";
    
    public final static String FUNCIONARIO_RESPONSAVEL = "funcionarioResponsavel";
    
    public final static String FUNCIONARIO_RESPONSAVEL_ID = "funcionarioResponsavel.id";
    
    public final static String UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional";
    
    public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
    
    public final static String EMAIL = "email";
    
    public final static String EMPRESA = "empresa";
    
    public final static String EMPRESA_ID = "empresa.id";
    
    public final static String FUNCIONARIO = "funcionario";
    
    public final static String FUNCIONARIO_ID = "funcionario.id";
    
    public final static String USUARIO_NOME = "nomeUsuario";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO = "solicitacaoAcessoSituacao";
    
    public final static String SOLICITACAO_ACESSO_SITUACAO_ID = "solicitacaoAcessoSituacao.id";
    
    public final static String PERIODO_INICIO = "periodoInicial";
    
    public final static String PERIODO_FINAL = "periodoFinal";
    
    public final static String DATA_SOLICITACAO = "dataSolicitacao";
    
    public final static String DATA_AUTORIZACAO = "dataAutorizacao";
    
    public final static String DATA_CADASTRAMENTO = "dataCadastramento";
    
	/**
     * Constructor for the FiltroSolicitacaoAcesso object
     */
    public FiltroSolicitacaoAcesso() {
    }

    /**
     * Constructor for the FiltroSolicitacaoAcesso object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoAcesso(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
}
