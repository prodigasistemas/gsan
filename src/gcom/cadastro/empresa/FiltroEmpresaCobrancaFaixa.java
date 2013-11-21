package gcom.cadastro.empresa;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroEmpresaCobrancaFaixa extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmpresaCobrancaFaixa(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroEmpresaCobrancaFaixa() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String EMPRESA_CONTRATO_COBRANCA = "empresaContratoCobranca";
    
    /**
     * Description of the Field
     */
    public final static String EMPRESA_CONTRATO_COBRANCA_ID = "empresaContratoCobranca.id";


    /**
     * Description of the Field
     */
    public final static String PERCENTUAL_FAIXA = "percentualFaixa";

    /**
     * Description of the Field
     */
    public final static String NUMERO_MAXIMO_CONTAS_FAIXA = "numeroMinimoContasFaixa";


}