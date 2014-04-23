package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de categorias de conta
 * 
 * @author  pedro alexandre
 * @created 03 de Janeiro de 2006
 */
public class FiltroContaContabil extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Construtor da classe FiltroContaCategoria
     */
    public FiltroContaContabil() {
    }

    /**
     * Construtor da classe FiltroContaCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaContabil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
