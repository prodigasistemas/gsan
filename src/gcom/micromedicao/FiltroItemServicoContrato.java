package gcom.micromedicao;

import gcom.util.filtro.Filtro;
import java.io.Serializable;

/**
 * Filtro Item Servico Contrato 
 *
 * @author Hugo Leonardo
 * @date 23/07/2010
 */
public class FiltroItemServicoContrato extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroItemServicoContrato object
     */
    public FiltroItemServicoContrato() {
    }

    /**
     * Constructor for the FiltroItemServicoContrato object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroItemServicoContrato(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Contrato Empresa Servico
     */
    public final static String ID = "id";
    
    /**
     * Item do Servico
     */
    public final static String ITEM_SERVICO = "itemServico";

    /**
     * Contrato
     */
    public final static String CONTRATO_EMPRESA_SERVICO = "contratoEmpresaServico";
    
    /**
     * Contrato
     */
    public final static String CONTRATO_EMPRESA_SERVICO_ID = "contratoEmpresaServico.id";
    
}
