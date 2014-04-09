package gcom.seguranca.transacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da TabelaLinhaColunaAlteracao
 * 
 * @author Thiago Toscano
 */
public class FiltroTabelaLinhaColunaAlteracao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroTabelaLinhaColunaAlteracao() {
    }

    /**
     * Constructor que recebe o parametro de ordenacao
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTabelaLinhaColunaAlteracao (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";
    
    public final static String TABELA_COLUNA = "tabelaColuna";
    
    public final static String TABELA_LINHA_ALTERACAO = "tabelaLinhaAlteracao";
    
    public final static String TABELA = "tabelaLinhaAlteracao.tabela";
    
    public final static String TABELA_ID = "tabelaLinhaAlteracao.tabela.id";    

    public final static String OPERACAO_EFETUADA = "tabelaLinhaAlteracao.operacaoEfetuada";
    
    public final static String OPERACAO_EFETUADA_ID = "tabelaLinhaAlteracao.operacaoEfetuada.id";
    
    public final static String ALTERACAO_TIPO_ID = "tabelaLinhaAlteracao.alteracaoTipo.id";
    
    public final static String ALTERACAO_TIPO = "tabelaLinhaAlteracao.alteracaoTipo";
}
