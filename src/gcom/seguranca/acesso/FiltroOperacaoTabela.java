package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da operação Tabela
 * 
 * @author Thiago Toscano
 * @date 23/03/2006
 */
public class FiltroOperacaoTabela extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroOperacaoTabela() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroOperacaoTabela(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }  
    
    public static String OPERACAO_ID = "operacao.id";

    public static String TABELA_ID = "tabela.id";
    
    public static String TABELA = "tabela";
    
    public static String TABELA_COLUNAS = "tabela.tabelaColunas";
    
    public static String OPERACAO = "operacao";
    
    public static String OPERACAO_ARGUMENTO_ID = "operacao.argumentoPesquisa.id";
    
    public static String OPERACAO_DESCRICAO = "operacao.descricao";
}
