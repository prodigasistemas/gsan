package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

/**
 * filtro de débitos cobrados
 * 
 * @author  pedro alexandre
 * @created 03 de Janeiro de 2006
 */
public class FiltroDebitoCobrado extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";
    
    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "conta.id";
    
    public final static String DEBITO_TIPO = "debitoTipo";

    /**
     * Description of the Field
     */
    public final static String FINANCIAMENTO_TIPO = "financiamentoTipo";
    public final static String FINANCIAMENTO_TIPO_ID = "financiamentoTipo.id";
    
    public final static String ANO_MES_REFERENCIA_DEBITO = "anoMesReferenciaDebito";
    
    public final static String VALOR_PRESTACAO = "valorPrestacao";

    /**
     * Construtor da classe FiltroDebitoCobrado
     */
    public FiltroDebitoCobrado() {
    }

    /**
     * Construtor da classe FiltroDebitoCobrado
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroDebitoCobrado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
