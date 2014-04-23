package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * Referente a Tabela MotivoRejeicao
 * 
 * @author Hugo Leonardo
 * @since 07/12/2010
 */
public class FiltroMotivoRejeicao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroMotivoRejeicao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroMotivoRejeicao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroMotivoRejeicao
     */
    public FiltroMotivoRejeicao() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String ULTIMA_ALTERACAO = "ultimaAlteracao";

    /**
     * Description of the Field
     */
    public final static String DESCRICAO = "descricao";
    
}
