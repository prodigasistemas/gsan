package gcom.seguranca.acesso.usuario;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * O filtro serve para armazenar os critérios de busca do usuario acao
 * 
 * @author Thiago Toscano
 */
public class FiltroUsuarioAcao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     * Constructor 
     */
    public FiltroUsuarioAcao() {
    }

    /**
     * Constructor 
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUsuarioAcao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Código 
     */
    public final static String ID = "id";
    
    public final static String INDICADO_USO = "indicadorUso";

}
