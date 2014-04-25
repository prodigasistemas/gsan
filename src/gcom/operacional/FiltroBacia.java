package gcom.operacional;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroBacia extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroBacia(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroQuadra
     */
    public FiltroBacia() {
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
    public final static String SISTEMA_ESGOTO = "sistemaEsgoto";
    
    /**
     * Description of the Field
     */
    public final static String SISTEMA_ESGOTO_ID = "sistemaEsgoto.id";

    /**
     * Description of the Field
     */
    public final static String INDICADORUSO = "indicadorUso";

}
