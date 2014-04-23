package gcom.seguranca.transacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca da TabelaLinhaAlteracao
 * 
 * @author Thiago Toscano
 */
public class FiltroTabelaLinhaAlteracao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroTabelaLinhaAlteracao() {
    }

    /**
     * Constructor que recebe o parametro de ordenacao
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroTabelaLinhaAlteracao (String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";
 
    public final static String TABELA = "tabela";
    
    public final static String TABELA_ID = "tabela.id";
    
    public final static String OPERACAO_EFETUADA = "operacaoEfetuada";
    
    public final static String OPERACAO_EFETUADA_ID = "operacaoEfetuada.id";
    
    public final static String ALTERACAO_TIPO_ID = "alteracaoTipo.id";
    
    public final static String INDICADOR_PRINCIPAL = "indicadorPrincipal";
    
    public final static String ID_1 = "id1";
    
    public final static String ID_2 = "id2";
}
