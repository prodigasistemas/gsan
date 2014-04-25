package gcom.faturamento.debito;

import gcom.util.filtro.Filtro;

/**
 * objeto utilizado na pesquisa de débito crédito situção
 * 
 * @author Pedro Alexandre
 * @created 23 de março de 2006
 */
public class FiltroDebitoCreditoSituacao extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroDebitoCreditoSituacao
     */
    public FiltroDebitoCreditoSituacao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricaoDebitoCreditoSituacao";
    
    
    /**
     * Construtor da classe FiltroDebitoCreditoSituacao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroDebitoCreditoSituacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}

