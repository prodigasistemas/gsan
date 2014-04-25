package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroAreaConstruidaFaixa extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "id";

    /**
     * Description of the Field
     */
    public final static String MENOR_FAIXA = "menorFaixa";

    /**
     * Description of the Field
     */
    public final static String MAIOR_FAIXA = "maiorFaixa";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroAreaConstruidaFaixa() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroAreaConstruidaFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
