package gcom.faturamento.credito;

import gcom.util.filtro.Filtro;

/**
 * filtro de pesquisa de créditos realizados histórico
 * 
 * @author  Pedro Alexandre
 * @date    30 de Março de 2006
 */
public class FiltroCreditoRealizadoHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";
    
    /**
     * Description of the Field
     */
    public final static String CONTA_HISTORICO_ID = "contaHistorico.id";

    /**
     * Construtor da classe FiltroCreditoRealizadoHistorico
     */
    public FiltroCreditoRealizadoHistorico() {
    }

    /**
     * Construtor da classe FiltroCreditoRealizadoHistorico
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCreditoRealizadoHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
