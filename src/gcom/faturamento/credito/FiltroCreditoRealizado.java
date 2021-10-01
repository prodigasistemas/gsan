package gcom.faturamento.credito;

import gcom.util.filtro.Filtro;

/**
 * filtro de pesquisa de créditos realizados
 * 
 * @author  pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class FiltroCreditoRealizado extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";
    
    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "conta.id";
    
    public final static String CREDITO_TIPO  = "creditoTipo";
    
    public final static String CREDITO_TIPO_ID  = "creditoTipo.id";
    
    public final static String LANCAMENTO_ITEM_CONTABIL = "lancamentoItemContabil.id";

    /**
     * Construtor da classe FiltroCreditoRealizado
     */
    public FiltroCreditoRealizado() {
    }

    /**
     * Construtor da classe FiltroCreditoRealizado
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroCreditoRealizado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
