package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroEspecificacaoTipoValidacao 
 *
 * @author Rafael Santos
 * @date 26/07/2006
 */
public class FiltroEspecificacaoTipoValidacao extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroEspecificacaoTipoValidacao object
     */
    public FiltroEspecificacaoTipoValidacao() {
    }

    /**
     * Constructor for the FiltroEspecificacaoTipoValidacao object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroEspecificacaoTipoValidacao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String ID_SOLICITACAO_TIPO_ESPECIFICACAO_ID = "solicitacaoTipoEspecificacao.id";
    
    public final static String CODIGO_CONSTANTE = "codigoConstante";

}
