package gcom.seguranca.transacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da TabelaColuna 
 * 
 * @author Thiago Toscano
 */
public class FiltroTabelaColuna extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroTabelaColuna() {
    }

    /**
     * Constructor que recebe o parametro de ordenacao
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTabelaColuna (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da tabela coluna
     */
    public final static String ID = "id";

    /**
     * nome da tabela coluna
     */
    public final static String COLUNA = "coluna";

    /**
     * Código da tabela
     */
    public final static String TABELA = "tabela";
    
    /**
     * Código da tabela
     */
    public final static String TABELA_ID = "tabela.id";
    /**
     * INDICADOR CHAVE PRIMARIA
     */
    public final static String INDICADOR_CHAVE_PRIMARIA = "indicadorPrimaryKey";
    
    public final static String DESCRICAO_COLUNA = "descricaoColuna";
    
    public final static String INDICADOR_PODE_RETIFICAR_CONTA = "indicadorPodeRetificarConta";
    
    public final static String INDICADOR_ATUALIZACAO_CADASRAL = "indicadorAtualizacaoCadastral";
    
}
