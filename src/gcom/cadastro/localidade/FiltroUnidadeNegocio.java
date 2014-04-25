package gcom.cadastro.localidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da entendidade Unidade de Negocio 
 * 
 * @author Rafael Santos
 * @since 11/10/2006
 */
public class FiltroUnidadeNegocio extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUnidadeNegocio(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroUnidadeNegocio() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String NOME = "nome";

    /**
     * Description of the Field
     */
    public final static String NOME_ABREVIADO = "nomeAbreviado";    
    
    /**
     * Description of the Field
     */
    public final static String ID_GERENCIA = "gerenciaRegional.id";
    
    /**
     * Description of the Field
     */
    public final static String GERENCIA = "gerenciaRegional";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE = "cliente";
    
    /**
     * Description of the Field
     */
    public final static String CLIENTE_ID = "cliente.id";
    
    /**
     * Description of the Field
     */
    public final static String CNPJ = "cnpj";
    
    

}
