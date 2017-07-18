package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;


/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 29/03/2006
 */
public class FiltroDebitoCobradoHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";
    
    /**
     * Description of the Field
     */
    public final static String CONTA_HISTORICO_ID = "contaHistorico.id";
    
    public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";

    public final static String DEBITO_TIPO = "debitoTipo";

    public final static String DEBITO_TIPO_DESCRICAO = "debitoTipo.descricao";
    
    public final static String ANO_MES_REFERENCIA_DEBITO = "anoMesReferenciaDebito";
    
    public final static String VALOR_PRESTACAO = "valorPrestacao";
    
    /**
     * Construtor da classe FiltroDebitoCobradoHistorico
     */
    public FiltroDebitoCobradoHistorico() {
    }

    /**
     * Construtor da classe FiltroDebitoCobradoHistorico
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroDebitoCobradoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
