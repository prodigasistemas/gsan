package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Filtro da entendidade Permissao Especial. 
 * 
 * @author Hugo Leonardo
 * @since 13/07/2010
 */
public class FiltroPermissaoEspecial extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroPermissaoEspecial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroPermissaoEspecial() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
    /**
     * Description of the Field
     */
    public final static String OPERACAO = "operacao"; 

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";    
    
}
