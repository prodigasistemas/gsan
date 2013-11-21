package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaContaUnidadeNegocio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaContaUnidadeNegocio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaContaUnidadeNegocio() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta";
    
    public final static String ID_COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta.id";
    
    public final static String ID_UNIDADE_NEGOCIO = "unidadeNegocio.id";
    
    public final static String UNIDADE_NEGOCIO = "unidadeNegocio";

    public final static String COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comp_id.cobrancaBoletimMedicaoId";
    
    public final static String COMP_ID_UNIDADE_NEGOCIO_ID = "comp_id.unidadeNegocioId";
    
}
