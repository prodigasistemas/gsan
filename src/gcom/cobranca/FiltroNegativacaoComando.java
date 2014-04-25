package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * Filtro Referente a Tabela NEGATIVACAO_COMANDO
 * 
 * @author Thiago Vieira
 * @created 27/12/2007
 */

public class FiltroNegativacaoComando extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for the FiltroCobrancaAtividade object
     */
    public FiltroNegativacaoComando() {
    }

    /**
     * Constructor for the FiltroCobrancaAtividade object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroNegativacaoComando(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
 
    /** persistent field */
    public final static String INDICADOR_COMANDO_CRITERIO = "indicadorComandoCriterio";

    /** persistent field */
    public final static String DATA_PREVISTA = "dataPrevista";

    /** nullable persistent field */
    public final static String DATA_HORA_COMANDO = "dataHoraComando";

    /** nullable persistent field */
    public final static String DATA_HORA_REALIZACAO = "dataHoraRealizacao";

    /** nullable persistent field */
    public final static String QUANTIDADE_INCLUSOES = "quantidadeInclusoes";

    /** nullable persistent field */
    public final static String VALOR_DEBITO = "valorDebito";

    /** nullable persistent field */
    public final static String QUANTIDADE_ITENS_INCLUIDOS = "quantidadeItensIncluidos";

    /** nullable persistent field */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /** nullable persistent field */
    public final static String DESCRICAO_COMUNICACAO_INTERNA = "descricaoComunicacaoInterna";

    /** persistent field */
    public final static String INDICADOR_SIMULACAO = "indicadorSimulacao";

    /** persistent field */
    public final static String ID_USUARIO = "usuario.id";

    /** persistent field */
    public final static String ID_NEGATIVADOR = "negativador.id";
    
}
