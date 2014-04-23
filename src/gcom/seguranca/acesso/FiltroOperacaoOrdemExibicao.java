package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca de Operacao Ordem Exibicao
 * 
 * @author Francisco Nascimento
 * @date 01/11/07
 */
public class FiltroOperacaoOrdemExibicao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroOperacaoOrdemExibicao() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroOperacaoOrdemExibicao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }  
    
    public static String OPERACAO = "operacao";
    
    public static String OPERACAO_ID = "operacao.id";

    public static String TABELA_COLUNA = "tabelaColuna";
    
    public static String TABELA_COLUNA_TABELA = "tabelaColuna.tabela";
    
    public static String TABELA_COLUNA_TABELA_ID = "tabelaColuna.tabela.id";
    
    public static String TABELA_COLUNA_ID = "tabelaColuna.id";
}
