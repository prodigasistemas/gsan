package gcom.seguranca.transacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da Tabela 
 * 
 * @author Thiago Toscano
 */
public class FiltroTabela extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroTabela() {
    }

    /**
     * Constructor que recebe o parametro de ordenacao
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTabela(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";
    
    /**
     * Campo descricao
     */
    public final static String DESCRICAO = "descricao";
    
    public final static String NOME = "nomeTabela";
    
    public final static String TABELA_COLUNAS = "tabelaColunas";
}
