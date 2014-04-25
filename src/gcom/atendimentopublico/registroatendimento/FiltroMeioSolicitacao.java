package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroMeioSolicitacao 
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class FiltroMeioSolicitacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroMeioSolicitacao object
     */
    public FiltroMeioSolicitacao() {
    }

    /**
     * Constructor for the FiltroMeioSolicitacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMeioSolicitacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";

    public final static String INDICADOR_USO = "indicadorUso";

}
