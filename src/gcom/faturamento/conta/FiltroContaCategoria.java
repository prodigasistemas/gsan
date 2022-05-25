package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de categorias de conta
 * 
 * @author  pedro alexandre
 * @created 03 de Janeiro de 2006
 */
public class FiltroContaCategoria extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "comp_id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    public final static String CATEGORIA = "comp_id.categoria";
    
    public final static String SUBCATEGORIA = "comp_id.subcategoria";
    
    public final static String CATEGORIA_ID = "comp_id.categoria.id";
    
    public final static String SUBCATEGORIA_ID = "comp_id.subcategoria.id";
    
    public final static String CATEGORIA_DESCRICAO = "comp_id.categoria.descricao";
    /**
     * Description of the Field
     */
    public final static String CONTA = "comp_id.conta";
    
    public final static String CONTA_ID = "comp_id.conta.id";
    
    public final static String QUANTIDADE_ECONOMIA = "quantidadeEconomia";
    
    public final static String IMOVEL = "comp_id.conta.imovel";

    /**
     * Construtor da classe FiltroContaCategoria
     */
    public FiltroContaCategoria() {
    }

    /**
     * Construtor da classe FiltroContaCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
