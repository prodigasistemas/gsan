package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de categorias de conta
 * 
 * @author  pedro alexandre
 * @created 03 de Janeiro de 2006
 */
public class FiltroContaCategoriaPK extends Filtro {
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CATEGORIA = "categoria";
    
    public final static String CATEGORIA_DESCRICAO = "categoria.descricao";
    
    public final static String CATEGORIA_ID = "categoria.id";
    
    public final static String SUBCATEGORIA_ID = "subcategoria.id";
    /**
     * Description of the Field
     */
    public final static String CONTA_ID = "conta.id";

    /**
     * Construtor da classe FiltroContaCategoria
     */
    public FiltroContaCategoriaPK() {
    }

    /**
     * Construtor da classe FiltroContaCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaCategoriaPK(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}