package gcom.micromedicao;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um item de serviço
 * 
 * @author Rodrigo Cabral
 */
public class FiltroItemServico extends Filtro {
	
	private static final long serialVersionUID = 1L;
    public FiltroItemServico() {
    }

    /**
     * Construtor da classe FiltroProfissao
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroItemServico(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     *  Description of the Field
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO_ABREVIADA = "descricaoAbreviada";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";

    /**
     * Description of the Field
     */
    public final static String CODIGO_CONSTANTE_CALCULO = "codigoConstanteCalculo";
    
    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /**
     * Description of the Field
     */
    public final static String CODIGO_ITEM = "codigoItem";


}
