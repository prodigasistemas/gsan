package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroMeioSolicitacao 
 *
 * @author Ana Maria	
 * @date 19/08/2006
 */
public class FiltroRAMotivoReativacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroMeioSolicitacao object
     */
    public FiltroRAMotivoReativacao() {
    }

    /**
     * Constructor for the FiltroMeioSolicitacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroRAMotivoReativacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";

    public final static String INDICADOR_USO = "indicadorUso";

}
