package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaContaExtensao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaContaExtensao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaContaExtensao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String USUARIO = "usuario";
    
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta";
    
    public final static String ID_COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta.id";
    
    public final static String DATA_EXECUCAO = "dataExecucao";
    
    public final static String REFERENCIA_INICIAL = "referenciaContaInicial";
    
    public final static String REFERENCIA_FINAL = "referenciaContaFinal";
    
    public final static String EMPRESA = "comandoEmpresaCobrancaConta.empresa";


}
