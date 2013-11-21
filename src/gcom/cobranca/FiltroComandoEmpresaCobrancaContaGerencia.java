package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaContaGerencia extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaContaGerencia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaContaGerencia() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta";
    
    public final static String ID_COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta.id";
    
    public final static String ID_GERENCIA_REGIONAL = "gerenciaRegional.id";
    
    public final static String GERENCIA_REGIONAL = "gerenciaRegional";

    public final static String COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comp_id.comandoEmpresaCobrancaContaId";
    
    public final static String COMP_ID_GERENCIA_REGIONAL_ID = "comp_id.gerenciaRegionalId";
}
