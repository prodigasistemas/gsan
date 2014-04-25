package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca do usuario alteracao
 * 
 * @author Thiago Toscano
 */
public class FiltroOperacaoEfetuada extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroOperacaoEfetuada() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroOperacaoEfetuada(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código 
     */
    public final static String ID = "id";

    public final static String OPERACAO = "operacao";

    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    public final static String OPERACAO_NOME_ARGUMENTO = OPERACAO + ".argumentoPesquisa";
    
    public final static String OPERACAO_ARGUMENTO_PESQUISA_TABELA = OPERACAO + "." + FiltroOperacao.ARGUMENTO_PESQUISA_TABELA;    
    
    public final static String OPERACAO_FUNCIONALIDADE_ID = OPERACAO + ".funcionalidade.id";

    /**
     * Código 
     */

    public final static String OPERACAO_ID = "operacao.id";
    
    public final static String USUARIO_ALTERACAO = "ua";
    
    public final static String TABELA_LINHA_ALTERACAO = "tla";

    public final static String TABELA_LINHA_ALTERACAO_ID1 = "tla.id1";

    public final static String TABELA_LINHA_COLUNA_ALTERACAO = "tblca";
       
    
    public final static String USUARIO = USUARIO_ALTERACAO + ".usuario";
    
    public final static String USUARIO_ID = USUARIO_ALTERACAO + ".usuario.id";

    public final static String USUARIO_TIPO = USUARIO_ALTERACAO + ".usuario.usuarioTipo";
    
    public final static String USUARIO_ACAO = USUARIO_ALTERACAO + ".usuarioAcao";

    public final static String USUARIO_ALTERACAO_ID = USUARIO_ALTERACAO + ".id";

    
    public final static String TABELA_ID = TABELA_LINHA_ALTERACAO + ".tabela.id";
    
    public final static String TABELA_COLUNA_ID= TABELA_LINHA_COLUNA_ALTERACAO + ".tabelaColuna.id";
    
    public final static String ARGUMENTO_VALOR = "argumentoValor";

    public final static String UNIDADE_NEGOCIO = ".usuario.unidadeNegocio";
    
}
