package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaConta extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaConta(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaConta() {
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
    public final static String DATA_EXECUCAO = "dataExecucao";
    /**
     * Description of the Field
     */
    public final static String COBRANCA_SITUACAO = "cobrancaSituacao";

}
