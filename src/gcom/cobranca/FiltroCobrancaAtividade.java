package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro Referente a Tabela Cobranca_Atividade
 * 
 * @author Rafael Santos
 * @created 22/02/2006
 */

public class FiltroCobrancaAtividade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividade object
     */
    public FiltroCobrancaAtividade() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroCobrancaAtividade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
 
    /**
     * Description of the Field
     */
    public final static String INDICADOR_EXECUCAO = "indicadorExecucao";
    
    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoCobrancaAtividade";    
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String ORDEM_REALIZACAO = "ordemRealizacao";
    
    public final static String INDICADOR_CRONOGRAMA = "indicadorCronograma";
    
    public final static String INDICADOR_OBRIGATORIEDADE = "indicadorObrigatoriedade";
    
    public final static String ID_PROCESSO = "processo.id";
    
    public final static String ID_COBRANCA_ACAO = "cobrancaAcao.id";
}
