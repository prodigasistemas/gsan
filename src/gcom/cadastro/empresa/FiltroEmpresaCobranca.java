package gcom.cadastro.empresa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEmpresaCobranca extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmpresaCobranca(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEmpresaCobranca() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String EMPRESA = "empresa";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA_ID = "empresa.id";


    /**
     * Description of the Field
     */
    public final static String PERCENTUAL_CONTRATO_COBRANCA = "percentualContratoCobranca";

    /**
     * Description of the Field
     */
    public final static String DATA_INICIO_CONTRATO = "dataInicioContrato";

    /**
     * Description of the Field
     */
    public final static String DATA_FIM_CONTRATO = "dataFinalContrato";
    
    

}