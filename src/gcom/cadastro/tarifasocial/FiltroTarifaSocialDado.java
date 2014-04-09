package gcom.cadastro.tarifasocial;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroTarifaSocialDado extends Filtro implements
        Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe FiltroTarifaSocialCartaoTipo
     */
    public FiltroTarifaSocialDado() {
    }

    /**
     * Construtor da classe FiltroTabelaAuxiliar
     * 
     * @param campoOrderBy
     *            Descrição do parâmetro
     */
    public FiltroTarifaSocialDado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

}
