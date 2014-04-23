package gcom.faturamento;

import gcom.util.filtro.Filtro;

/**
 * Filtro Consumo Faixa Categoria
 * 
 * @author  Rafael Francisco Pinto
 * 
 * @created 15/06/2007
 */
public class FiltroConsumoFaixaCategoria extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NUMERO_FAIXA_INICIO = "numeroFaixaInicio";

    /**
     * Description of the Field
     */
    public final static String NUMERO_FAIXA_FIM = "numeroFaixaFim";
    
    
    public final static String ID_CATEGORIA_TIPO = "categoria.categoriaTipo.id";
    
    
    public final static String ID_CATEGORIA = "categoria.id";
    
    /**
     * Construtor da classe FiltroImpostosDeduzidos
     */
    public FiltroConsumoFaixaCategoria() {
    }

    /**
     * Construtor da classe FiltroImpostosDeduzidos
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroConsumoFaixaCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
}
