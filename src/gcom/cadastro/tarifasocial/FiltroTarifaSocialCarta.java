package gcom.cadastro.tarifasocial;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTarifaSocialCarta extends Filtro implements
        Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroTarifaSocialCartaoTipo
     */
    public FiltroTarifaSocialCarta() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTarifaSocialCarta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel";
    
    
    public final static String DATA_EXECUCAO_COMANDO = "tarifaSocialComandoCarta.dataExecucao";
    
    
    public final static String DATA_GERACAO_COMANDO = "tarifaSocialComandoCarta.dataGeracao";
    
    public final static String TARIFA_SOCIAL_COMANDO_CARTA = "tarifaSocialComandoCarta";

}
