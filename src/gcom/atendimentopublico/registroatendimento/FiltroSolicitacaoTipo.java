package gcom.atendimentopublico.registroatendimento;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * FiltroSolicitacaoTipo 
 *
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class FiltroSolicitacaoTipo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Constructor for the FiltroSolicitacaoTipo object
     */
    public FiltroSolicitacaoTipo() {
    }

    /**
     * Constructor for the FiltroSolicitacaoTipo object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroSolicitacaoTipo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";

    public final static String DESCRICAO = "descricao";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String SOLICITACAO_TIPO_GRUPO_ID = "solicitacaoTipoGrupo.id";
    
    public final static String INDICADOR_FALTA_AGUA = "indicadorFaltaAgua";
    
    public final static String INDICADOR_TARIFA_SOCIAL = "indicadorTarifaSocial";
    
    public final static String INDICADOR_USO_SISTEMA = "indicadorUsoSistema";
    
    public final static String SOLICITACAO_TIPO_GRUPO = "solicitacaoTipoGrupo";


}
