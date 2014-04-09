package gcom.faturamento.consumotarifa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoTarifaVigencia extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoTarifaVigencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroConsumoTarifaVigencia() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String CONSUMO_TARIFA_ID = "consumoTarifa.id";

    /**
     * Description of the Field
     */
    public final static String DATA_VIGENCIA = "dataVigencia";

    public final static String CONSUMO_TARIFA_DESCRICAO = "consumoTarifa.descricao";

    public final static String CONSUMO_TARIFA = "consumoTarifa";
    
    public final static String CONSUMO_TARIFA_LIGACAO_AGUA_PERFIL = "consumoTarifa.ligacaoAguaPerfil";
    

}
