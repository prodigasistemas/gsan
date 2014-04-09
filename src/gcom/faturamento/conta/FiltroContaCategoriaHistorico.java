package gcom.faturamento.conta;

import gcom.util.filtro.Filtro;

/**
 * filtro de categorias de conta
 * 
 * @author  pedro alexandre
 * @created 03 de Janeiro de 2006
 */
public class FiltroContaCategoriaHistorico extends Filtro {
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String CODIGO = "comp_id";

    /**
     * Description of the Field
     */
    public final static String CONTA = "comp_id.contaHistorico";
    
    public final static String CONTA_ID = "comp_id.contaHistorico.id";

    /**
     * Construtor da classe FiltroContaCategoria
     */
    public FiltroContaCategoriaHistorico() {
    }

    /**
     * Construtor da classe FiltroContaCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroContaCategoriaHistorico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
