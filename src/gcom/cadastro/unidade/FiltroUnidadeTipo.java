package gcom.cadastro.unidade;

import gcom.util.filtro.Filtro;

import java.io.Serializable;


/**
 * FiltroUnidadeTipo 
 *
 * @author Rafael Pinto
 * @date 25/07/2006
 */
public class FiltroUnidadeTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroUnidadeOrganizacional object
     */
    public FiltroUnidadeTipo() {
    }

    /**
     * Constructor for the FiltroUnidadeTipo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroUnidadeTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Id do Unidade
     */
    public final static String ID = "id";

    /**
     * Descrição da Unidade
     */
    public final static String DESCRICAO = "descricao";

    /**
     * Descrição da IndicadorUso
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Descrição da Nivel
     */
    public final static String NIVEL = "nivel";

}
