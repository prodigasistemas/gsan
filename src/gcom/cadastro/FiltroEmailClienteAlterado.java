package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernando Fontelles
 */
public class FiltroEmailClienteAlterado extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEmailClienteAlterado(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroEmailClienteAlterado() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ID_CLIENTE = "idCliente";

    /**
     * Description of the Field
     */
    public final static String NOME_CLIENTE_ANTERIOR = "nomeClienteAnterior";

    /**
     * Description of the Field
     */
    public final static String CPF_ANTERIOR = "cpfAnterior";

    /**
     * Description of the Field
     */
    public final static String CNPJ_ANTERIOR = "cnpjAnterior";
    
    public final static String EMAIL_ANTERIOR = "emailAnterior";

    /**
     * Description of the Field
     */
    public final static String NOME_SOLICITANTE = "nomeSolicitante";
    
    public final static String CPF_SOLICITANTE = "cpfSolicitante";
    
    public final static String NOME_CLIENTE_ATUAL = "nomeClienteAtual";
    
    public final static String CPF_ATUAL = "cpfAtual";
    
    public final static String CNPJ_ATUAl = "cnpjAtual";
    
    public final static String EMAIL_ATUAL = "emailAtual";

}
