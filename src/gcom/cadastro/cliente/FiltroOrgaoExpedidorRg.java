package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

/**
 * O filtro serve para armazenar os critérios de busca de um cliente
 * 
 * @author Rodrigo
 */
public class FiltroOrgaoExpedidorRg extends Filtro {
	
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroOrgaoExpedidorRg
     */
    public FiltroOrgaoExpedidorRg() {
    }

    /**
     * Construtor da classe FiltroOrgaoExpedidorRg
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroOrgaoExpedidorRg(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código da unidade de medição
     */
    public final static String ID = "id";

    /**
     * Description of the Field
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

}
