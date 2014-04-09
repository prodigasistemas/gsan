package gcom.micromedicao;

import gcom.util.filtro.Filtro;
import java.io.Serializable;

/**
 * FiltroContratoEmpresaAditivo 
 *
 * @author Mariana Victor
 * @date 22/11/2010
 */
public class FiltroContratoEmpresaAditivo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroContratoEmpresaAditivo object
     */
    public FiltroContratoEmpresaAditivo() {
    }

    /**
     * Constructor for the FiltroContratoEmpresaAditivo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroContratoEmpresaAditivo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }
    
    /**
     * Id do Contrato Empresa Aditivo
     */
    public final static String ID = "id";
    
    public final static String CONTRATO_EMPRESA_SERVICO = "contratoEmpresaServico";
    

}
