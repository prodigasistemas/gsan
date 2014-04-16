package gcom.cadastro.tarifasocial;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTarifaSocialDadoEconomia extends Filtro implements
        Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Construtor da classe FiltroTarifaSocialDadoEconomia
     */
    public FiltroTarifaSocialDadoEconomia() {
    }

    /**
     * Construtor da classe FiltroTarifaSocialDadoEconomia
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTarifaSocialDadoEconomia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
//    public final static String IMOVEL_ID = "tarifaSocialDado.imovel.id";
    
    public final static String IMOVEL_ECONOMIA = "imovelEconomia";
    
    public final static String IMOVEL_ECONOMIA_ID = "imovelEconomia.id";
    
    public final static String TARIFA_SOCIAL_CARTAO_TIPO = "tarifaSocialCartaoTipo";
    
    public final static String TARIFA_SOCIAL_CARTAO_TIPO_ID = "tarifaSocialCartaoTipo.id";
    
    public final static String RENDA_TIPO = "rendaTipo";
    
    public final static String TARIFA_SOCIAL_DADO = "tarifaSocialDado";
    
    public final static String TARIFA_SOCIAL_DADO_IMOVEL = "tarifaSocialDado.imovel";
    
    public final static String IMOVEL = "imovel";
    
    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String NUMERO_CARTAO_PROGRAMA_SOCIAL = "numeroCartaoProgramaSocial";
    
    public final static String DATA_EXCLUSAO = "dataExclusao";
    
    public final static String MOTIVO_REVISAO = "tarifaSocialRevisaoMotivo";
    
    public final static String MOTIVO_EXCLUSAO = "tarifaSocialExclusaoMotivo";
    
}
