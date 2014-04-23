package gcom.cadastro.cliente;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Description of the Class
 * 
 * @author Sávio Luiz
 * @created 17 de Agosto de 2005
 */

public class FiltroClienteImovelEconomia extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroClienteImovelEconomia
     */
    public FiltroClienteImovelEconomia() {
    }

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroClienteImovelEconomia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DATA_FIM_RELACAO = "dataFimRelacao";
    
    /**
     * Description of the Field
     */
    public final static String DATA_INICIO_RELACAO = "dataInicioRelacao";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIA_ID = "imovelEconomia.id";
    
    public final static String IMOVEL_ECONOMIA = "imovelEconomia";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL = "imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL_INDICADOR_USO = "imovelEconomia.imovelSubcategoria.comp_id.imovel.imovelPerfil.indicadorUso";

    /**
     * Description of the Field
     */
    public final static String FIM_RELACAO_MOTIVO = "clienteImovelFimRelacaoMotivo.id";

    /**
     * Description of the Field
     */
    public final static String CLIENTE_RELACAO_TIPO = "clienteRelacaoTipo.id";

    /**
     * Description of the Field
     */
    public final static String CLIENTE_ID = "cliente.id";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovelEconomia.imovelSubcategoria.comp_id.imovel.id";
    
    public final static String IMOVEL = "imovelEconomia.imovelSubcategoria.comp_id.imovel";

    public final static String SUBCATEGORIA_ID = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.id";

    public final static String IMOVEL_ECONOMIEA_AREA_CONSTRUIDA_FAIXA = "imovelEconomia.areaConstruidaFaixa";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIEA_IMOVEL_SUB_CATEGORIA = "imovelEconomia.imovelSubcategoria";

    /**
     * Description of the Field
     */
    public final static String IMOVEL_ECONOMIEA_IMOVEL_CATEGORIA = "imovelEconomia.imovelSubcategoria.comp_id.subcategoria.categoria";

}
