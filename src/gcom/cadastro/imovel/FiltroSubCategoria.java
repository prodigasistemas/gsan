package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroSubCategoria extends Filtro {
	
	private static final long serialVersionUID = 1L;

    /**
     * Description of the Field
     */
    public final static String CODIGO = "codigo";
    
    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String INDICADOR_CONSUMO_TARIFA = "indicadorTarifaConsumo";

    /**
     * Description of the Field
     */
    public final static String CATEGORIA_ID = "categoria.id";

    /**
     * Description of the Field
     */
    public final static String CATEGORIA = "categoria";
    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";
    
    /**
     * Description of the Field
     */
    public final static String CODIGO_TARIFA_SOCIAL = "codigoTarifaSocial";
    
    /**
     * Description of the Field
     */
    public final static String CODIGO_GRUPO_SUBCATEGORIA = "codigoGrupoSubcategoria";
    
    /**
     * Description of the Field
     */
    public final static String NUMERO_FATOR_FISCALIZACAO = "numeroFatorFiscalizacao";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_TARIFA_CONSUMO = "indicadorTarifaConsumo";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_SAZONALIDADE = "indicadorSazonalidade";
    
    /**
     * Construtor da classe FiltroCategoria
     */
    public FiltroSubCategoria() {
    }

    /**
     * Construtor da classe FiltroCategoria
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroSubCategoria(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

}
