package gcom.cadastro;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Hugo Leonardo
 */
public class FiltroContaBraile extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroContaBraile object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroContaBraile(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroContaBraile
     */
    public FiltroContaBraile() {
    }

    public final static String ID = "id";

    public final static String IMOVEL_ID = "imovel.id";
    
    public final static String IMOVEL = "imovel";
    
    public final static String EMAIL = "email";

    public final static String NOME_CLIENTE = "nomeCliente";
    
    public final static String CPF_CLIENTE = "cpfCliente";
    
    public final static String CNPJ_CLIENTE = "cnpjCliente";

    public final static String NOME_SOLICITANTE = "nomeSolicitante";
  
    public final static String CPF_SOLICITANTE = "cpfSolicitante";
    
    public final static String RA_ID = "registroAtendimento.id";
    
    public final static String RA = "registroAtendimento";
    
}
