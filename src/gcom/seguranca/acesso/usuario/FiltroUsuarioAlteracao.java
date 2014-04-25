package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca do usuario alteracao
 * 
 * @author Thiago Toscano
 */
public class FiltroUsuarioAlteracao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroUsuarioAlteracao() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuarioAlteracao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código 
     */
    public final static String ID = "id";

    public final static String OPERACAO = "operacaoEfetuada.operacao";
    
    public final static String OPERACAO_ID = "operacaoEfetuada.operacao.id";

    public final static String OPERACAO_EFETUADA = "operacaoEfetuada";
       
    public final static String USUARIO = "usuario";
    
    public final static String USUARIO_ID = "usuario.id";

    public final static String USUARIO_TIPO = "usuario.usuarioTipo";
    
    public final static String USUARIO_FUNCIONARIO = "usuario.funcionario";
    
    public final static String USUARIO_ACAO = "usuarioAcao";

    public final static String OPERACAO_EFETUADA_ID = "operacaoEfetuada.id";

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String USUARIO_ALTERACAO_ID = "usuarioAcao.id";
    
    
    public final static String ALIAS_TABELA_LINHA_ALTERACAO = "tla";
    
    public final static String ALIAS_TABELA_LINHA_COLUNA_ALTERACAO = "tblca";
    
    public final static String TABELA_ID= ALIAS_TABELA_LINHA_ALTERACAO + ".tabela.id";
    
    public final static String TABELA_COLUNA_ID= ALIAS_TABELA_LINHA_COLUNA_ALTERACAO + ".tabelaColuna.id";
    
    
    public final static String OPERACAO_EFETUADA_ULTIMA_ALTERACAO = "operacaoEfetuada.ultimaAlteracao";
 
    public final static String EMPRESA = "empresa";

    public final static String EMPRESA_ID = "empresa.id";

}
